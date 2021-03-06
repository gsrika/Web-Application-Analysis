

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.BranchHandle;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.GotoInstruction;
import org.apache.bcel.generic.IfInstruction;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.InstructionTargeter;
import org.apache.bcel.generic.LOOKUPSWITCH;
import org.apache.bcel.generic.RETURN;
import org.apache.bcel.generic.ReturnInstruction;
import org.apache.bcel.generic.Select;
import org.apache.bcel.generic.TABLESWITCH;

public class CFG {
	// Static Dotty file strings.
	protected static final String[] dottyFileHeader = new String[] {
		"digraph control_flow_graph {",
		"",
		"	node [shape = rectangle]; entry exit;",
		"	node [shape = circle];",
		""
	};
	protected static final String[] dottyFileFooter = new String[] {
		"}"
	};
	protected static final String dottyEntryNode = "entry";
	protected static final String dottyExitNode = "exit";
	// Dotty file edge templates.
	protected static final String dottyLineFormat = "	%1$s -> %2$s;%n";
	protected static final String dottyLineLabelFormat = "	%1$s -> %2$s [label = \"%3$s\"];%n";

	// Map associating line number with instruction.
	SortedMap<Integer, InstructionHandle> statements = new TreeMap<Integer, InstructionHandle>();
	Map<String,Integer>targetMap=new HashMap<String,Integer>();
	InstructionHandle []instructionHandleArray;
	Map<Integer,List<Integer> > adj=new TreeMap<Integer,List<Integer>>();

	/**
	 * Loads an instruction list and creates a new CFG.
	 * 
	 * @param instructions Instruction list from the method to create the CFG from.
	 * 
	 * 
	 * 
	 */
	
	
	
	public void DFS(int start,int end,String s,int visited[])
	{
		int key=start;
		
		
		if(adj.containsKey(key))
		{
			List<Integer> edge=adj.get(key);
			visited[start]=1;
			for(int i=0;i<edge.size();i++)
			{
				int temp=edge.get(i);
				
				if(temp==-1)
					return;
				else if(temp==end)
				{
					
						System.out.print(s+"-->"+Integer.toString(temp));
						
					
					System.out.print("\n");
					return;
					
				}
				else 
				{
					if(visited[temp]==0)
					DFS(temp,end,s+"-->"+Integer.toString(temp),visited);
				}
				
				
			}
					
			
		}
		
		
	}
	public CFG( InstructionList instructions ) {
		
		instructionHandleArray =instructions.getInstructionHandles();
		for (int i=0;i<instructionHandleArray.length;i++)
		{
			int pos=instructionHandleArray[i].getPosition();
		   statements.put(pos,instructionHandleArray[i]);
		}	
		
			
		
				
		
	}

	/**
	 * Generates a Dotty file representing the CFG.
	 * 
	 * @param out OutputStream to write the dotty file to.
	 */
	public void generateDotty( OutputStream _out ) {
		
		
		PrintStream ps=new PrintStream(_out);
		
		
		for(String s:dottyFileHeader)
		{
			ps.print(s);
			ps.print("\n");
		}
		
		ps.print("	"+"entry"+" -> "+instructionHandleArray[0].getPosition()+";");
		ps.print('\n');
		for(int i=0;i<instructionHandleArray.length-1;i++)
					
		{
					

			Instruction instruction=instructionHandleArray[i].getInstruction();
			
			int start,end;
			//System.out.print("he");
			
			start=instructionHandleArray[i].getPosition();
			
			if(instruction instanceof BranchInstruction)
			{
				//System.out.print("he");
				if(instruction instanceof IfInstruction)
				{
					
				   ps.print("	"+instructionHandleArray[i].getPosition()+ " -> "+((IfInstruction) instruction).getTarget().getPosition()+";");
				   ps.print("\n");
				   
				    
				    end=((IfInstruction) instruction).getTarget().getPosition();
				   putData(start, end);
				   
				   ps.print("	"+instructionHandleArray[i].getPosition()+" -> "+instructionHandleArray[i+1].getPosition()+";");
				   ps.print("\n");
				   end=instructionHandleArray[i+1].getPosition();
				   putData(start, end);
				}
				if(instruction instanceof GotoInstruction)
				{
					ps.print("	"+instructionHandleArray[i].getPosition()+ " -> "+((GotoInstruction) instruction).getTarget().getPosition()+";");
					ps.print("\n");
					end=((GotoInstruction) instruction).getTarget().getPosition();
					   putData(start, end);
					
					
					   
				}
				if(instruction instanceof LOOKUPSWITCH)
				{
					
					
					InstructionHandle hd[]=((LOOKUPSWITCH)instruction).getTargets();
					for(int j=0;j<hd.length;j++)
					{
						
						ps.print("	"+instructionHandleArray[i].getPosition()+ " -> "+hd[j].getPosition()+";");
						ps.print("\n");
						end=hd[j].getPosition();
						putData(start, end);
					}
					
					ps.print("	"+instructionHandleArray[i].getPosition()+ " -> "+((LOOKUPSWITCH) instruction).getTarget().getPosition()+";");
					ps.print("\n");
					end=((LOOKUPSWITCH) instruction).getTarget().getPosition();
					putData(start, end);
				}
				if(instruction instanceof TABLESWITCH)
				{
					
					InstructionHandle hd[]=((TABLESWITCH)instruction).getTargets();
					for(int j=0;j<hd.length;j++)
					{
						
						ps.print("	"+instructionHandleArray[i].getPosition()+ " -> "+hd[j].getPosition()+";");
						ps.print("\n");
						end=hd[j].getPosition();
						putData(start, end);
					}
					
					ps.print("	"+instructionHandleArray[i].getPosition()+ " -> "+((TABLESWITCH) instruction).getTarget().getPosition()+";");
					ps.print("\n");
					end=((TABLESWITCH) instruction).getTarget().getPosition();
					putData(start, end);
					
				}
				
				
				
			}
			else
			{
				if(instruction instanceof ReturnInstruction)
				{
					
					ps.print("	"+instructionHandleArray[i].getPosition()+" -> "+"exit;");
					ps.print("\n");
					putData(start, -1);
					
					
				}
				else
				{
					ps.print("	"+instructionHandleArray[i].getPosition()+" -> "+instructionHandleArray[i+1].getPosition()+";");
					ps.print("\n");
					end=instructionHandleArray[i+1].getPosition();
					putData(start, end);
					
					
				}
					
			}
			
			
		}
		int length=instructionHandleArray.length;
		ps.print("	"+instructionHandleArray[length-1].getPosition()+" -> "+"exit"+";");
		ps.print('\n');
		putData(instructionHandleArray[length-1].getPosition(), -1);
		
		for(String s:dottyFileFooter)
		{
			ps.print(s);
			ps.print("\n");
		}
		
		ps.close();
		
		for(Integer key: adj.keySet())
		{
			System.out.println(key+"--->"+adj.get(key));
		}
		
		
	}

	public void putData(int start, int end) {
		if(adj.containsKey(start))
		   {
			   List a=adj.get(start);
			   a.add(end);
		   }
		   else
		   {
			   List<Integer>temp=new ArrayList<Integer>();
			   temp.add(end);
			   adj.put(start, temp);
		   }
	}


	/**
	 * Main method. Generate a Dotty file with the CFG representing a given class file.
	 * 
	 * @param args Expects two arguments: <input-class-file> <output-dotty-file>
	 */
	public static void main(String[] args) {
		PrintStream error = System.err;
		PrintStream debug = new PrintStream( new OutputStream() {

			@Override
			public void write(int arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}} );

		// Check arguments.
		if ( args.length != 2 ) {
			error.println( "Wrong number of arguments." );
			error.println( "Usage: CFG <input-class-file> <output-dotty-file>" );
			System.exit( 1 );
		}
		String inputClassFilename = args[0];
		String outputDottyFilename = args[1];

		// Parse class file.
		debug.println( "Parsing " + inputClassFilename + "." );
		JavaClass cls = null;
		try {
			cls = (new ClassParser( inputClassFilename )).parse();
		} catch (IOException e) {
			e.printStackTrace( debug );
			error.println( "Error while parsing " + inputClassFilename + "." );
			System.exit( 1 );
		}

		// Search for main method.
		debug.println( "Searching for main method:" );
		Method mainMethod = null;
		for ( Method m : cls.getMethods() ) {
			debug.println( "   " + m.getName() );
			if ( "main".equals( m.getName() ) ) {
				mainMethod = m;
				break;
			}
		}
		if ( mainMethod == null ) {
			error.println( "No main method found in " + inputClassFilename + "." );
			System.exit( 1 );
		}

		
	//	InstructionList il=new InstructionList(mainMethod.getCode().getCode());
		//System.out.println(il.toString());
		// Create CFG.
		debug.println( "Creating CFG object." );
		CFG cfg = new CFG( new InstructionList( mainMethod.getCode().getCode() ) );

		// Output Dotty file.
		debug.println( "Generating Dotty file." );
		try {
			OutputStream output = new FileOutputStream( outputDottyFilename );
			cfg.generateDotty( output );
			output.close();
		} catch (IOException e) {
			e.printStackTrace( debug );
			error.println( "Error while writing to " + outputDottyFilename + "." );
			System.exit( 1 );
		}
		List<Integer>li=new ArrayList<Integer>();
		int [] visited=new int[547];
		for(int i=0;i<visited.length;i++)
		{
			visited[i]=0;
		}
		cfg.DFS(62,493,"62",visited);
		debug.println( "Done." );
	}
}