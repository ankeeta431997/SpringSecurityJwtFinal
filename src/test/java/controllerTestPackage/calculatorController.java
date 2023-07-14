package controllerTestPackage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.mockito.Mock;
import com.unoveo.securityjwt.calculator.calculatorService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import com.unoveo.securityjwt.calculator.ExpressionResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class calculatorController {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private calculatorService calculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        calculator = new calculatorService();
        System.out.println("BEFORE EACH METHOD CALL");
    }
//
//    @Test
//    void doPost_withValidRequest_returnsCorrectResult() throws ServletException, IOException {
//        // Arrange
//        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("[{\"type\":\"num\",\"value\":\"5\"},{\"type\":\"op\",\"value\":\"+\"},{\"type\":\"num\",\"value\":\"7\"}]")));
//       // when(request.getReader()).thenReturn(new BufferedReader(new StringReader("[{\"type\":\"num\",\"value\":\"10\"},{\"type\":\"op\",\"value\":\"-\"},{\"type\":\"num\",\"value\":\"5\"}]")));
//        when(request.isUserInRole("ROLE_ADMIN")).thenReturn(true);
//
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter writer = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(writer);
//
//        // Act
//        calculator.doPost(request, response);
//
//        // Assert
//        verify(response).setContentType("application/json");
//        writer.flush();
//        ExpressionResponse expressionResponse = new Gson().fromJson(stringWriter.toString(), ExpressionResponse.class);
//        System.out.println("result after test "+expressionResponse);
//        assertNotNull(expressionResponse);
//        assertEquals(12, expressionResponse.getResult());
//       // assertEquals(5, expressionResponse.getResult());
//      //  assertEquals(HttpServletResponse.SC_OK, response.getStatus());
//    }
@Test
void runTestsFromExcel() throws ServletException, IOException {
    Workbook workbook = WorkbookFactory.create(new File("src/test/testData/test.xls"));
    Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

    for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
        Row row = sheet.getRow(rowNum);
        int id = (int) row.getCell(0).getNumericCellValue();
        String expressionRequest = row.getCell(1).getStringCellValue();
        double expected = row.getCell(2).getNumericCellValue();

        // Arrange
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(expressionRequest)));
        when(request.isUserInRole("ROLE_ADMIN")).thenReturn(true);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        // Act
        calculator.doPost(request, response);

        // Assert
       //verify(response).setContentType("application/json");
        writer.flush();
        ExpressionResponse expressionResponse = new Gson().fromJson(stringWriter.toString(), ExpressionResponse.class);
        //System.out.println("Result after test " + expressionResponse);
        assertNotNull(expressionResponse);
        System.out.println("expected result :"+expected);
        System.out.println("Actual Result :"+expressionResponse.getResult());
        assertEquals(expected, expressionResponse.getResult());
    }
}
    @Test
    void doPost_withInvalidUserRole_returnsForbiddenError () throws ServletException, IOException {
        // Arrange
        when(request.isUserInRole("ROLE_ADMIN")).thenReturn(false);

        // Act
        calculator.doPost(request, response);

        // Assert
        verify(response).sendError(403, "Admin Access Required.");
        verify(response).setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

}
