package Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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
            File file = new File("C:/Users/kamus/Documents/NetBeansProjects/Warehouse/credentials/admin-key.json");
            if (!file.exists()) {
                System.out.println("Error: El archivo de credenciales no existe en la ruta especificada.");
                return;
            }
            System.out.println("Archivo JSON encontrado en la ruta especificada.");
            
            FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\kamus\\Documents\\NetBeansProjects\\WarehouseManager\\credentials\\admin-key.json")))
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
    public static void pushProductToCategory(Product product, String category) {
        System.out.println("Inicializando Firebase...");
        initFirebase();

        if (firebaseDatabase == null) {
            System.out.println("Error: FirebaseDatabase es null, no se puede continuar.");
            return;
        }

        // Referencia a la ruta /categorias/categoria_del_producto/<nombre_producto>
        DatabaseReference productReference = firebaseDatabase.getReference("categories")
                .child(category)
                .child(product.getName());

        // Pushea el producto a la base de datos
        productReference.setValue(product, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.out.println("Error al guardar el producto: " + databaseError.getMessage());
                } else {
                    System.out.println("Producto guardado exitosamente en la categoría " + category);
                }
            }
        });
    }

    // Método para recuperar todos los productos de una categoría
    public static void getProductsByCategory(String category) {
        System.out.println("Inicializando Firebase...");
        initFirebase();

        if (firebaseDatabase == null) {
            System.out.println("Error: FirebaseDatabase es null, no se puede continuar.");
            return;
        }

        // Referencia a la ruta /categories/categoria_del_producto
        DatabaseReference categoryReference = firebaseDatabase.getReference("categories").child(category);

        // Obtiene todos los productos de la categoría
        categoryReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    System.out.println("Productos en la categoría " + category + ":");
                    for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                        Product product = productSnapshot.getValue(Product.class);
                        System.out.println(product); // Aquí puedes modificar para mostrar los productos de la forma que prefieras
                    }
                } else {
                    System.out.println("No se encontraron productos en la categoría " + category);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Error al recuperar productos: " + error.getMessage());
            }
        });
    }
}