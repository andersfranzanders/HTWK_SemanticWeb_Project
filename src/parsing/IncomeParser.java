package parsing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import classModel.ResourceClass;
import classModel.State;

public class IncomeParser implements Parser {

	String incomeExcel = "resources/statistic_id255174_verfuegbares-einkommen-privater-haushalte-je-einwohner-in-den-bundeslaendern-2012.xlsx";

	@Override
	public void parse(List<ResourceClass> resourceList) {
		try {
			InputStream inp = new FileInputStream(incomeExcel);
			Workbook wb = WorkbookFactory.create(inp);

			Sheet sheet = wb.getSheetAt(1);

			double grundeinkommen = 0;
			String stateName = "";

			for (int row = 0; row < 26; row++) {
				Row currentRow = sheet.getRow(row);

				for (int column = 0; column < 3; column++) {

					try {

						Cell theCell = currentRow.getCell(column);
						int cellType = theCell.getCellType();

						if (cellType == 0) {
							//System.out.println(theCell.getNumericCellValue());
							grundeinkommen = theCell.getNumericCellValue();

						}
						if (cellType == 1) {
							//System.out.println(theCell.getStringCellValue());
							stateName = theCell.getStringCellValue();
						}
						for (ResourceClass resource : resourceList) {

							if (resource instanceof State) {
								State state = (State) resource;
								if (state.getName().equals(stateName)) {
									// System.out.println(stateName);
									state.setIncome(grundeinkommen);
								}
							}
						}
					} catch (Exception e) {
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

	}
}
