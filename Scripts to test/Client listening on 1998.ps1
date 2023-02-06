$port = 1998
$buffer = New-Object byte[] 1024
$client = New-Object System.Net.Sockets.TcpClient
$client.Connect("127.0.0.1", $port)
$stream = $client.GetStream()
while($true) {
    $read = $stream.Read($buffer, 0, $buffer.Length)
    if ($read -gt 0) {
        $received = [System.Text.Encoding]::ASCII.GetString($buffer, 0, $read)
        Write-Host $received
    }
}
$client.Close()
