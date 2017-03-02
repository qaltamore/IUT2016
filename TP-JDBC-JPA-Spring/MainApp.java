package fr.testjpa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class MainApp implements CommandLineRunner {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	//private Zoo zoo;

	public static void main(String[] args) {
		new SpringApplication(MainApp.class).run(args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		try (Connection cx = dataSource.getConnection()) {
			try(PreparedStatement stmt = cx.prepareStatement("select 'Hello'")) {
				ResultSet rs = stmt.executeQuery();
				rs.next();
				String res = rs.getString(1);
				System.out.println(res);
				
				/*Animal animal = zoo.findOne(1);
				if(animal != null)
					System.out.println("Nom : " + animal.getName() + " ; Cri : " + animal.getShout());
				else {*/
				Animal animal = new Animal();
				animal.setName("Lion");
				animal.setShout("roarrr");
				//zoo.save(animal);
				try {
					entityManager.persist(animal);
					//entityManager.persist(zoo);
					entityManager.flush();
					
					Animal animal2 = entityManager.find(Animal.class, 0);
					System.out.println("Nom : " + animal2.getName() + " ; Cri : " + animal2.getShout());
					
					System.out.println("\n\nEverything went well\n\n");
				} catch(Exception e) {
					System.out.println(e.toString());
					System.out.println("pb entity manager");
				}
				//}
				
			}
		}
	}

}
