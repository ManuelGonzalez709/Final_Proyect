import mysql.connector
from mysql.connector import Error

def update_photo_urls(old_ip, new_ip, db_config):
    try:
        # Conectar a la base de datos
        connection = mysql.connector.connect(
            host=db_config['host'],
            database=db_config['database'],
            user=db_config['user'],
            password=db_config['password']
        )

        if connection.is_connected():
            cursor = connection.cursor()
            
            # Seleccionar todas las URLs de fotos que contengan la IP antigua
            select_query = "SELECT id, fotos FROM anuncios WHERE fotos LIKE %s"
            cursor.execute(select_query, (f"%{old_ip}%",))
            records = cursor.fetchall()

            for record in records:
                id_anuncio = record[0]
                fotos = record[1]
                
                # Reemplazar la IP antigua con la nueva en las URLs de fotos
                nuevas_fotos = fotos.replace(old_ip, new_ip)

                # Actualizar las URLs de fotos en la base de datos
                update_query = "UPDATE anuncios SET fotos = %s WHERE id = %s"
                cursor.execute(update_query, (nuevas_fotos, id_anuncio))
            
            connection.commit()
            print(f"Actualización completada: {cursor.rowcount} registros actualizados.")

    except Error as e:
        print(f"Error al conectar a la base de datos: {e}")
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()
            print("Conexión a la base de datos cerrada.")

if __name__ == "__main__":
    # Configuración de la base de datos
    db_config = {
        'host': 'localhost',
        'database': 'anunciaya',
        'user': 'root',
        'password': ''
    }

    # Pedir la IP antigua y nueva por consola
    old_ip = input("Introduce la IP antigua: ")
    new_ip = input("Introduce la nueva IP: ")

    # Llamar a la función para actualizar las URLs de las fotos
    update_photo_urls(old_ip, new_ip, db_config)
