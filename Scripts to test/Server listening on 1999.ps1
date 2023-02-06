$port = 1999
$buffer = New-Object byte[] 1024
$server = New-Object System.Net.Sockets.TcpListener($port)
$server.Start()
$client = $server.AcceptTcpClient()
$stream = $client.GetStream()
while($true) {
    $read = $stream.Read($buffer, 0, $buffer.Length)
    if ($read -gt 0) {
        $received = [System.Text.Encoding]::ASCII.GetString($buffer, 0, $read)
        Write-Host $received
    }
}
$client.Close()
$server.Stop()
