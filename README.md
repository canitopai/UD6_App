# UD6_App

Para usar el proyecto necesario runear la api, descargandola del repo : https://github.com/U-tad2021/ServerProduct, acceder al directorio donde lo has extraido y ejecutar 
gradlew bootRun.

FUNCIONALIDADES:
Buscador operativo, solo busca una vez, no refresca, para eliminar el filtro pulsar la X del buscador.
BottomNavigationMenu operativo, con navhost hace la navegacion entre ListFragment y FavFragment.
Desde ListFragment, al hacer click a un item se va a su detalle por id.
Desde FavFragment, al hacer click a un item se elimina de favoritos.
Pulsar el boton flotante de ListFragment para agregar un item a la api.
Pulsar en el detalle el botón de favoritos para agregar a la lista (comprueba si lo has agregado previamente)
Si quedan <5 productos en stock se muestra un mensaje de pocas unidades.
Si el discountPrice es menor que el regularPrice se muestra el descuento.
