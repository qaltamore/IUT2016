import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LaunchTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testDataSource() throws SQLException {
		Assert.assertNotNull(dataSource);
		try (Connection cx = dataSource.getConnection()) {
			Assert.assertNotNull(cx);
			
			try (PreparedStatement stmt = cx.prepareStatement("SELECT 'Hello'")) {
				ResultSet rs = stmt.executeQuery();
				rs.next();
				String res = rs.getString(1);
				Assert.assertEquals("Hello", res);
			}
		}
	}
}
