Mi trabajo se divide en tres vistas: vista de autenticación, lista de productos y vista para el CRUD de los productos.
vista de autenticación: En esta vista utilicé firebase y realice la autenticación manualmente recuperando el usuario y su
contraseña y validandola desde java. Separe mi código en vista:AuthView.java, modelo-vista:AuthController.java y 
Model:FirebaseServices.java

![login](https://github.com/user-attachments/assets/0c538663-a008-4f02-8746-c3513ce417b8)

vista de lista de productos: En esta vista utilicé firebase para recuperar los productos guardados manualmente en firebase, y los 
almacené en una lista List ya que me pareció conveniente para poder ordenarlos bajo diferentes parámetros; en mi caso, Los productos
se pueden ordenar por unidades y por precio. 
Separé mi código en vista: ProductsView.java, modelo-vista: ProductsController.java y Model:FirebaseServices.java.

![products](https://github.com/user-attachments/assets/56ed086f-9106-4e24-9a0a-53fbc629690b)

vista de operaciones CRUD para los productos: Para esta vista como en las anteriores, usé firebase y sus librerias quienes facilitan 
las operaciones CRUD, permito eliminar, modificar y crear productos mediante la GUI, ademas de usar al máximo los beneficios de 
las librerias de firebase para mantener información sobre la operación.
separé mi código en vista: ActionsView.java, modelo-vista: ProductsController.java y Model: FirebaseServices.java.

![Acciones](https://github.com/user-attachments/assets/7fd0d618-f39d-4977-bf49-a3244abc64ab)

Por ultimo, traté de hacer la interfaz amigable y coherente entre vistas para mejor experiencia del usuario.


