import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainTest {

    @Mock
    ArrayList<Integer> mockList;

    @Test
    void main() {
        when(mockList.get(0)).thenReturn(69);

        assertEquals(69, mockList.get(0));
    }
}