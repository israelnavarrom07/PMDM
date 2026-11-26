
<?php
require_once __DIR__ . '/datos/ConexionBD.php';

header('Content-Type: application/json; charset=utf-8');

$id = filter_var($_GET['id'] ?? '', FILTER_VALIDATE_INT);

if ($id === false) {
    http_response_code(400);
    echo json_encode(['error' => ERROR_FALTA_ID], JSON_UNESCAPED_UNICODE);
    exit;
}

try {
    $bd = new ConexionBD();

    $consulta = $bd->getConexion()->prepare(
        'SELECT id, nombre, resultado, imagen FROM freestylers WHERE id = ?'
    );

    $consulta->execute([$id]);

    $fila = $consulta->fetch();

    if ($fila === false) {
        http_response_code(404);
        echo json_encode(['error' => ERROR_NO_EXISTE], JSON_UNESCAPED_UNICODE);
        exit;
    }

    echo json_encode($fila, JSON_UNESCAPED_UNICODE);

} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(['error' => ERROR_CONEXION], JSON_UNESCAPED_UNICODE);
}
