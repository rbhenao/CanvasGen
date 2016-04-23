package canvasgen.app.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import canvasgen.app.view.canvasview.canvasObject.CanvasObject;
import canvasgen.app.view.canvasview.canvasObject.CanvasState;
import canvasgen.app.view.canvasview.canvasObject.Circle;
import canvasgen.app.view.canvasview.canvasObject.EraseLine;
import canvasgen.app.view.canvasview.canvasObject.FreeDrawLine;
import canvasgen.app.view.canvasview.canvasObject.Line;
import canvasgen.app.view.canvasview.canvasObject.RectangleObj;

public class CodeGenerator {

	private static ArrayList<String> finalOutput;
	private static int varCount = 0;

	public static void generateOutputFile(String filePath, HashMap<String, CanvasObject> canvasObjects){
		ArrayList<String> compilerCode = getCanvasGeneratorFile();

		ArrayList<String> finalOutput = new ArrayList<String>();

		//create the onload function that all code goes inside
		CanvasState c = (CanvasState) canvasObjects.get("-1");
		String canvasName = c.getCanvasName();
		int canvasWidth = c.getCanvasWidth();
		int canvasHeight = c.getCanvasHeight();
		canvasObjects.remove("-1");
		ArrayList<String> onload = fetchCodeBlock("onload", compilerCode);
		onload = setValues(onload, canvasName+"Id",canvasName, canvasWidth+"", canvasHeight+"");

		for(String line: onload){
			finalOutput.add(line);
		}
			
		//System.out.println(Runtime.getRuntime().freeMemory());
		
		for(Entry<String, CanvasObject> entry : canvasObjects.entrySet()){
			CanvasObject obj = entry.getValue();
			if(obj instanceof FreeDrawLine){
				//moveContext tag
				FreeDrawLine f = (FreeDrawLine)obj;
				int[] x = f.getxPoints();
				int[] y = f.getyPoints();
				ArrayList<String> moveContext = fetchCodeBlock("moveContext", compilerCode);
				moveContext = setValues(moveContext, x[0]+"",y[1]+"");
				moveContext = setVariables(moveContext);	
				for(String line: moveContext){
					finalOutput.add(line);
				}
				
				//lineTo tag
				for(int i = 0; i < x.length; i++){
					ArrayList<String> lineTo = fetchCodeBlock("lineTo", compilerCode);
					lineTo = setValues(lineTo, x[i]+"",y[i]+"");
					lineTo = setVariables(lineTo);
					for(String line: lineTo){
						finalOutput.add(line);
					}
				}
				
				//stroke tag
				int color = f.getBackgroundColor();
				String hexColor = String.format("#%06X", (0xFFFFFF & color));
				int stroke = f.getStroke();
				ArrayList<String> strokeBlock = fetchCodeBlock("stroke", compilerCode);
				strokeBlock = setValues(strokeBlock,stroke+"",hexColor);
				strokeBlock = setVariables(strokeBlock);
				
				for(String line: strokeBlock){
					finalOutput.add(line);
				}			
			}else if(obj instanceof Circle){			
				//circle tag
				Circle circle = (Circle)obj;
				int radius = (int)(circle.getWidth()/2);
				int x = circle.getCoordinates()[0];
				int y = circle.getCoordinates()[1];
				int stroke = 1;//circle.getStroke();
				int color = circle.getBackgroundColor();
				String hexColor = String.format("#%06X", (0xFFFFFF & color));
				ArrayList<String> circleBlock = fetchCodeBlock("circle", compilerCode);
				circleBlock = setValues(circleBlock,x+"",y+"",radius+"",stroke+"",hexColor);
				circleBlock = setVariables(circleBlock);
				
				for(String line: circleBlock){
					finalOutput.add(line);
				}

			}else if(obj instanceof RectangleObj){			
				//circle tag
				RectangleObj rectangle = (RectangleObj)obj;
				int x = rectangle.getX();
				int y = rectangle.getY();
				int width = rectangle.getWidth();
				int height = rectangle.getHeight();
				int stroke = 1;//circle.getStroke();
				int color = rectangle.getBackgroundColor();
				String hexColor = String.format("#%06X", (0xFFFFFF & color));
				ArrayList<String> rectangleBlock = fetchCodeBlock("rectangle", compilerCode);
				rectangleBlock = setValues(rectangleBlock,x+"",y+"",width+"",height+"",stroke+"",hexColor);
				rectangleBlock = setVariables(rectangleBlock);
				
				for(String line: rectangleBlock){
					finalOutput.add(line);
				}
			}else if(obj instanceof Line){
				Line line = (Line)obj;
				int x1 = line.getX1();
				int y1 = line.getY1();
				int x2 = line.getX2();
				int y2 = line.getY2();
				
				System.out.println(x1);
				System.out.println(y1);
				
				//moveContext tag			
				ArrayList<String> moveContext = fetchCodeBlock("moveContext", compilerCode);
				moveContext = setValues(moveContext, x1+"",y1+"");
				moveContext = setVariables(moveContext);	
				for(String str: moveContext){
					finalOutput.add(str);
				}
				
				//lineTo tag
				
				ArrayList<String> lineTo = fetchCodeBlock("lineTo", compilerCode);
				lineTo = setValues(lineTo, x2+"",y2+"");
				lineTo = setVariables(lineTo);
				for(String str: lineTo){
					finalOutput.add(str);
				}
				
				
				//stroke tag
				int stroke = 1;//line.getStroke();
				int color = line.getBackgroundColor();
				String hexColor = String.format("#%06X", (0xFFFFFF & color));
				System.out.println(hexColor);
				ArrayList<String> strokeBlock = fetchCodeBlock("stroke", compilerCode);
				strokeBlock = setValues(strokeBlock,stroke+"",hexColor);
				strokeBlock = setVariables(strokeBlock);
				
				for(String str: strokeBlock){
					finalOutput.add(str);
				}
			}
		}
		
		for(Entry<String, CanvasObject> entry : canvasObjects.entrySet()){
			CanvasObject obj = entry.getValue();
			if(obj instanceof EraseLine){
				System.out.println("erase line!");
				//moveContext tag
				EraseLine f = (EraseLine)obj;
				int[] x = f.getxPoints();
				int[] y = f.getyPoints();
				ArrayList<String> moveContext = fetchCodeBlock("moveContext", compilerCode);
				moveContext = setValues(moveContext, x[0]+"",y[1]+"");
				moveContext = setVariables(moveContext);	
				for(String line: moveContext){
					finalOutput.add(line);
				}
				
				//lineTo tag
				for(int i = 0; i < x.length; i++){
					ArrayList<String> lineTo = fetchCodeBlock("lineTo", compilerCode);
					lineTo = setValues(lineTo, x[i]+"",y[i]+"");
					lineTo = setVariables(lineTo);
					for(String line: lineTo){
						finalOutput.add(line);
					}
				}
				
				//stroke tag
				int color = f.getBackgroundColor();
				String hexColor = String.format("#%06X", (0xFFFFFF & color));
				int stroke = 5;//f.getStroke();
				ArrayList<String> strokeBlock = fetchCodeBlock("stroke", compilerCode);
				strokeBlock = setValues(strokeBlock,stroke+"",hexColor);
				strokeBlock = setVariables(strokeBlock);
				
				for(String line: strokeBlock){
					finalOutput.add(line);
				}			
			}
		}

		//close the onload function
		ArrayList<String> closeOnLoad = fetchCodeBlock("closeOnLoad", compilerCode);
		for(String line: closeOnLoad){
			finalOutput.add(line);
		}
		
		try {
			WriteFile.writeFile1(filePath, finalOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<String> setVariables(ArrayList<String> block) {
		ArrayList<String> result = new ArrayList<String>();
			for(String line: block){
				if(line.contains("$"))
					line = line.replaceAll("\\$", varCount+"");
				result.add(line);
			}
			varCount++;
		return result;
	}

	//get the compiler code file
	private static ArrayList<String> getCanvasGeneratorFile(){
		try{
			File dir = new File(".");



//			File fin = new File(dir.getCanonicalPath() + File.separator
//					+ "resources" + File.separator + "JS" + File.separator + "canvasGenLibrary.txt");
			ArrayList<String> lines = ReadFile.readFile1("/JS/canvasGenLibrary.txt");
			lines = cleanCodeFile(lines);
			return lines;
		}catch(IOException e){
			return null;
		}
	}

	//clean up the canvas generation code file so its ready to be parsed
	private static ArrayList<String> cleanCodeFile(ArrayList<String> lines){
		ArrayList<String> cleanFile = new ArrayList<String>();
		for(String line: lines){
			//remove empty lines
			if(line.length() == 0)
				continue;
			//remove comments
			if(line.trim().charAt(0) == '!')
				continue;
			cleanFile.add(line);
		}
		return cleanFile;
	}

	private static ArrayList<String> fetchCodeBlock(String tag, ArrayList<String> file){
		ArrayList<String> block = new ArrayList<String>();
		tag = tag.toLowerCase();
		boolean insideBlock = false;
		for(String line: file){
			if(line.trim().charAt(0) == '#')
				if(line.toLowerCase().contains(tag)){
					insideBlock = true;
					continue;
				}	
			if(insideBlock){
				if(line.trim().charAt(0) == '#')
					break;
				else
					block.add(line);
			}
		}	
		return block;
	}

	private static ArrayList<String> setValues(ArrayList<String> block, String...values) {
		ArrayList<String> result = new ArrayList<String>();
		int count = 0;
		for(String line: block){
			if(line.contains("= \"\"")){
				line = line.replaceAll("\"\"", "\"" + values[count] + "\"");
				count++;
			}
			result.add(line);
		}
		return result;
	}
}
