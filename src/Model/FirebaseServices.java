package Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import ModelView.AuthController;
import com.google.firebase.database.DataSnapshot;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import View.AuthView;
import View.ActionsView;
import java.util.List;
import java.util.ArrayList;
import ModelView.ActionsController;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;


/**
 *
 * @author kamus
 */
public class FirebaseServices {
    private static FirebaseDatabase firebaseDatabase;

    private static void initFirebase() {
        if (!FirebaseApp.getApps().isEmpty()) {
            System.out.println("Firebase ya está inicializado.");
            return;
        }
        
        try {
            File file = new File("C:/Users/kamus/Documents/NetBeansProjects/WarehouseManagerPoo/credentials/admin-key.json");
            if (!file.exists()) {
                System.out.println("Error: El archivo de credenciales no existe en la ruta especificada.");
                return;
            }
            System.out.println("Archivo JSON encontrado en la ruta especificada.");
            
            FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\kamus\\Documents\\NetBeansProjects\\WarehouseManagerPoo\\credentials\\admin-key.json")))
                    .setDatabaseUrl("https://warehousemanager-c07a0-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(firebaseOptions);
            firebaseDatabase = FirebaseDatabase.getInstance();
            System.out.println("La conexión se realizó exitosamente...");
        } catch (IOException | RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    // Método para agregar un producto a la categoría
    public static void pushProductToCategory(Product product, String category,ActionsView view) {
        System.out.println("Inicializando Firebase...");
        initFirebase();

        if (firebaseDatabase == null) {
            System.out.println("Error: FirebaseDatabase es null, no se puede continuar.");
            return;
        }

        // Referencia a la ruta /categorias/categoria_del_producto/<nombre_producto>
        DatabaseReference productReference = firebaseDatabase.getReference("category")
                .child(category)
                .child(product.getName());

        // Pushea el producto a la base de datos
        productReference.setValue(product, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.out.println("Error al guardar el producto: " + databaseError.getMessage());
                    ActionsController.showMessage("Error al guardar el producto",new Color(255,0,0),view);
                } else {
                    System.out.println("Producto guardado exitosamente en la categoría " + category);
                    ActionsController.showMessage("Producto guardado exitosamente",new Color(0,255,0),view);
                    
                    
                }
            }
        });
    }
    public static void updateProductAttribute(String categoryKey, String productKey, Double price, int units,ActionsView view) {
        System.out.println("Inicializando Firebase...");
        initFirebase();

        if (firebaseDatabase == null) {
            System.out.println("Error: FirebaseDatabase es null, no se puede continuar.");
            return;
        }
        DatabaseReference databaseRef = firebaseDatabase.getReference("category");
        DatabaseReference productRef = databaseRef.child(categoryKey).child(productKey);

        // Crear un mapa con la actualización
        Map<String, Object> updates = new HashMap<>();
        updates.put("price", price);
        updates.put("units", units);

        // Realizar la actualización con un CompletionListener
        productRef.updateChildren(updates, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                System.err.println("Error al actualizar el nodo: " + databaseError.getMessage());
                ActionsController.showMessage("Error al actualizar el producto",new Color(255,0,0),view);
            } else {
                System.out.println("Nodo actualizado correctamente.");
                ActionsController.showMessage("Producto actualizado correctamente",new Color(0,255,0),view);
            }
        });
    }
    public static void deleteProduct(String categoryKey, String productKey,ActionsView view) {
        System.out.println("Inicializando Firebase...");
        initFirebase();

        if (firebaseDatabase == null) {
            System.out.println("Error: FirebaseDatabase es null, no se puede continuar.");
            return;
        }
        DatabaseReference databaseRef = firebaseDatabase.getReference("category");
        DatabaseReference productRef = databaseRef.child(categoryKey).child(productKey);

        // Eliminar el nodo
        productRef.removeValue((databaseError, databaseReference) -> {
            if (databaseError != null) {
                System.err.println("Error al eliminar el nodo: " + databaseError.getMessage());
                ActionsController.showMessage("Error al eliminiar el producto",new Color(255,0,0),view);
            } else {
                System.out.println("Nodo eliminado correctamente.");
                ActionsController.showMessage("Producto eliminado correctamente",new Color(0,255,0),view);
            }
        });
    }
    // Método para recuperar todos los productos de una categoría
    public static void getAllProducts(ProductsCallback callback) {
        List<Product> allProducts = new ArrayList<>();
        System.out.println("Inicializando Firebase...");
        initFirebase();

        if (firebaseDatabase == null) {
            System.out.println("Error: FirebaseDatabase es null, no se puede continuar.");
            return;
        }

        DatabaseReference categoriesReference = firebaseDatabase.getReference("category");

        categoriesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    System.out.println("Recuperando todos los productos...");
                    for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                        for (DataSnapshot productSnapshot : categorySnapshot.getChildren()) {
                            Product product = productSnapshot.getValue(Product.class);
                            if (product != null) {
                          
                                allProducts.add(product);
                            }
                    }
                }

                    // Aquí puedes modificar para hacer lo que necesites con la lista de productos
                    System.out.println("Lista de productos:");
                    for (Product p : allProducts) {
                        System.out.println(p);
                    }
                    
                    callback.onProductsRetrieved(allProducts);

                } else {
                    System.out.println("No se encontraron productos en la base de datos.");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Error al recuperar productos: " + error.getMessage());
            }
        });
        
    
    }
    public static User getAdminByUsername(String username,AuthView view){
         System.out.println("Inicializando Firebase...");
        initFirebase();

        // Verifica que la conexión a Firebase haya sido exitosa
        if (firebaseDatabase == null) {
            System.out.println("Error: FirebaseDatabase es null, no se puede continuar.");
            return null;
        }

        // Obtiene la referencia a la ruta /users/<username>
        DatabaseReference userReference = firebaseDatabase.getReference("admin").child(username);

        // Crea un CountDownLatch para esperar que Firebase complete la lectura
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // Contenedor para almacenar el usuario recuperado
        final User[] retrievedUser = new User[1];

        // Intentamos obtener el usuario por su nombre de usuario
        System.out.println("Buscando al admin: " + username);
        try {
            userReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Si el usuario existe, lo mapeamos al objeto User
                        retrievedUser[0] = dataSnapshot.getValue(User.class);
                    } else {
                        AuthController.errorLogin("Usuario "+"'"+username+"'"+" no encontrado", view);
                    }
                    countDownLatch.countDown(); // Liberamos el CountDownLatch
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    AuthController.errorLogin("Error al iniciar sesión", view);
                    countDownLatch.countDown(); // Liberamos el CountDownLatch en caso de error
                    
                }
            });

            // Esperamos hasta que Firebase complete la operación o se agote el tiempo
            if (!countDownLatch.await(1000, TimeUnit.SECONDS)) {
                System.out.println("Tiempo de espera agotado: Firebase no respondió.");
                AuthController.errorLogin("Error al iniciar sesión", view);
                return null;
            }
        } catch (Exception e) {
            AuthController.errorLogin("Error al iniciar sesión", view);
            e.printStackTrace();
            return null;
        }

        // Devolvemos el usuario recuperado o null si no se encontró
        return retrievedUser[0];
    }
}