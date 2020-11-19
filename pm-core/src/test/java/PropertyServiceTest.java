import com.pm.core.property.model.Property;
import com.pm.core.property.repository.PropertyRepository;
import com.pm.core.property.services.PropertyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class PropertyServiceTest {

    @MockBean
    private PropertyRepository propertyRepository;

    @MockBean
    private PropertyService propertyService;

    @Test
    public void searchAll()
    {
        System.out.println("Intergration testing searchAll properties");
        List<Property> propertyList = null;
        try {
            propertyList = propertyService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        propertyList.forEach(a-> System.out.println(a.getTitle()));
    }
}
