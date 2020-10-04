// Muhammad Khizar
// CS2336
// Calculator Project- Project 2
// Prof: Kamran Khan
// Overview: This program creates a GUI calculator application
// it mimics the windows 10 programmer calculator
// The calculator performs many operations which include basic
// math operations, modulus, binary, hexadeciaml, decimal and octal conversions
// there are options to choose the format you want your answer.
// there are buttons to clear the information entered
// the precedence is like PEMDAS


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.Stack;

public class Main
{
	// main function 
	
	public static void main (String [] args) 
	{				
		// creating GUI
		
		GUI myCalc = new GUI();
	
		// adding an icon to the GUI app
		
		try 
		{
			// image from disk 
			
		   Image image = new ImageIcon("C:/Users/Khizar/Desktop/Coding/CalculatorGUI/icon.JPEG").getImage();
		   myCalc.setIconImage(image);
		}
		catch(Exception e)
		{
			
			// if not icon.. then 
			
		   System.out.println("Application icon not found");
		}
		
		// setting title basic information display
		
		myCalc.setTitle("Calculator");
		
		// setting window size
		
		myCalc.setSize(302, 500);
		
		// setting windows visible
		
		myCalc.setVisible(true);
		
		// not letting resize the window
		
		myCalc.setResizable(false);
		
		// setting decimal format as default
		
		myCalc.dec.setSelected(true);
		
		// when 'X' is clicked program closes
		
		myCalc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
    }
}

class GUI extends JFrame implements ActionListener, MouseListener
{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -1120939821197535899L;

	// 1 panel 
	
	JPanel myFirstPanel; // PANEL
	
	// all buttons
	
	JButton zero, one, two, three, four, five, six, seven, eight, nine,    // ALL THE BUTTONS
			dot, equal, plus, minus, multiply, divide, clear, tripleBar,
			plusMinus, c, backspace,p1, p2, letterA, letterB, letterC, 
			letterD, letterE, letterF, mod, upArrow, and, or, xor,
			not, rsh, lsh, menu1, menu2, word, ms, m;
	
	// radio buttons to choose between the hex dec bin oct
	
	JRadioButton hex, dec, oct, bin;
	
	// All texts 
	
	JTextField display;                     
	JLabel HEX, DEC, OCT, BIN, Prog, equation;
	
	// variables used to store clicking info
	
	String userInput = "";
	int input = 0, leftParanthesis = 0, rightParanthesis = 0, answer = 0;
	boolean oRepeat = true, P2 = false, Continue = false, ceRepeat = false;
	
public GUI()
	{
		// panel
	
		JPanel p = new JPanel();

		// all buttons
		
		zero = new JButton("0");
		one = new JButton("1");
		two = new JButton("2");
		three = new JButton("3");
		four = new JButton("4");
		five = new JButton("5");
		six = new JButton("6");
		seven = new JButton("7");
		eight = new JButton("8");
		nine = new JButton("9");
		equal = new JButton("=");
		plus = new JButton("+");
		minus = new JButton("-");
		multiply = new JButton("*");
		divide = new JButton("/");
		clear = new JButton("CE");
		dot = new JButton(".");
		tripleBar = new JButton("\u2261");
		Prog = new JLabel("PROGRAMMER");
		equation = new JLabel("");
		plusMinus = new JButton("\u00B1");
		c = new JButton("C");
		backspace = new JButton("\u232B");
		p1 = new JButton("(");
		p2 = new JButton(")");
		letterA = new JButton("A");
		letterB = new JButton("B");
		letterC = new JButton("C");
		letterD = new JButton("D");
		letterE = new JButton("E");
		letterF = new JButton("F");
		upArrow = new JButton("\u2191");
		mod = new JButton("Mod");
		lsh = new JButton("Lsh");
		rsh = new JButton("Rsh");
		or = new JButton("Or");
		xor = new JButton("Xor");
		not = new JButton("Not");
		and = new JButton("And");
		menu1 = new JButton("\u283F");
		menu2 = new JButton("\u2831");
		word = new JButton("WORD");
		ms = new JButton("MS");
		m = new JButton("M");
		hex = new JRadioButton("");
		dec = new JRadioButton("");
		bin = new JRadioButton("");
		oct = new JRadioButton("");
		
		// Setting font to make it look better
		
		zero.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		one.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		two.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		three.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		four.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		five.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		six.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		seven.setFont(new Font("Segoe UI", Font.BOLD, 15));
		eight.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		nine.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		plus.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		minus.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		multiply.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		divide.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		clear.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		dot.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		equal.setFont(new Font("Segoe UI", Font.BOLD, 15));
		Prog.setFont(new Font("Segoe UI", Font.BOLD, 15));  
		equation.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		tripleBar.setFont(new Font("Segoe UI", Font.BOLD, 20));
		plusMinus.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		c.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		p1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		p2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mod.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		tripleBar.setMargin(new Insets(0, 0, 0, 0));
		clear.setMargin(new Insets(0, 0, 0, 0));
		backspace.setMargin(new Insets(0, 0, 0, 0));
		mod.setMargin(new Insets(0, 0, 0, 0));
		lsh.setMargin(new Insets(0, 0, 0, 0));
		rsh.setMargin(new Insets(0, 0, 0, 0));
		and.setMargin(new Insets(0, 0, 0, 0));
		not.setMargin(new Insets(0, 0, 0, 0));
		or.setMargin(new Insets(0, 0, 0, 0));
		xor.setMargin(new Insets(0, 0, 0, 0));
		ms.setMargin(new Insets(0, 0, 0, 0));
		letterA.setForeground(Color.GRAY);
		letterB.setForeground(Color.GRAY);
		letterC.setForeground(Color.GRAY);
		letterD.setForeground(Color.GRAY);
		letterE.setForeground(Color.GRAY);
		letterF.setForeground(Color.GRAY);
		word.setForeground(Color.BLUE);		
		
		// display box
		
		display = new JTextField();	
		display.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// Hex dec binary oct display
		
		HEX = new JLabel("HEX 0");
		DEC = new JLabel("DEC 0");
		OCT = new JLabel("OCT 0");
		BIN = new JLabel("BIN 0");
		
		// aligning the display stuff
		
		equation.setHorizontalAlignment(SwingConstants.RIGHT);
		HEX.setHorizontalAlignment(SwingConstants.LEFT);
		DEC.setHorizontalAlignment(SwingConstants.LEFT);
		OCT.setHorizontalAlignment(SwingConstants.LEFT);
		BIN.setHorizontalAlignment(SwingConstants.LEFT);
		
		// layout to null
		
		p.setLayout(null);

		// creating first layer
		
		equal.setBounds(245, 432, 45, 35);
		dot.setBounds(197, 432, 45, 35);
		zero.setBounds(149, 432, 45, 35);
		plusMinus.setBounds(101, 432, 45, 35);
		p2.setBounds(53, 432, 45, 35);
		p1.setBounds(5, 432, 45, 35);

		// creating second layer
		
		plus.setBounds(245, 392, 45, 35);
		three.setBounds(197, 392, 45, 35);
		two.setBounds(149, 392, 45, 35);
		one.setBounds(101, 392, 45, 35);
		letterF.setBounds(53, 392, 45, 35);
		letterE.setBounds(5, 392, 45, 35);

		// creating third layer
		
		minus.setBounds(245, 352, 45, 35);
		six.setBounds(197, 352, 45, 35);
		five.setBounds(149, 352, 45, 35);
		four.setBounds(101, 352, 45, 35);
		letterD.setBounds(53, 352, 45, 35);
		letterC.setBounds(5, 352, 45, 35);
		
		// creating 4th layer
		
		multiply.setBounds(245, 312, 45, 35);
		nine.setBounds(197, 312, 45, 35);
		eight.setBounds(149, 312, 45, 35);
		seven.setBounds(101, 312, 45, 35);
		letterB.setBounds(53, 312, 45, 35);
		letterA.setBounds(5, 312, 45, 35);

		// creating 5th layer
		
		divide.setBounds(245, 272, 45, 35);
		backspace.setBounds(197, 272, 45, 35);
		c.setBounds(149, 272, 45, 35);
		clear.setBounds(101, 272, 45, 35);
		mod.setBounds(53, 272, 45, 35);
		upArrow.setBounds(5, 272, 45, 35);
		
		// 6th layer
		
		and.setBounds(245, 232, 45, 35);
		not.setBounds(197, 232, 45, 35);
		xor.setBounds(149, 232, 45, 35);
		or.setBounds(101, 232, 45, 35);
		rsh.setBounds(53, 232, 45, 35);
		lsh.setBounds(5, 232, 45, 35);
		
		// 7th layer
		
		m.setBounds(245, 192, 45, 35);
		ms.setBounds(197, 192, 45, 35);
		word.setBounds(103, 192, 90, 35);
		menu2.setBounds(53, 192, 45, 35);
		menu1.setBounds(5, 192, 45, 35);
		
		// displaying HEX DEC OCT BIN
		
		HEX.setBounds(25, 60, 302, 100);
		DEC.setBounds(25, 80, 302, 100);
		OCT.setBounds(25, 100, 302, 100);
		BIN.setBounds(25, 120, 302, 100);
		
		// radio buttons
		
		hex.setBounds(5, 100, 20, 20);
		dec.setBounds(5, 120, 20, 20);
		oct.setBounds(5, 140, 20, 20);
		bin.setBounds(5, 160, 20, 20);
		
		// display box
		
		Prog.setBounds(46, -9, 150, 50);
		equation.setBounds(140, 0, 150, 50);
		tripleBar.setBounds(10, 7, 20, 20);
		display.setBounds(3, 35, 290, 60);
		
		// coordinates are like: (x, y, width, height);
		
		// adding numbers
		
		p.add(zero);
		p.add(one);
		p.add(two);
		p.add(three);
		p.add(four);
		p.add(five);
		p.add(six);
		p.add(seven);
		p.add(eight);
		p.add(nine);

		// adding actions to the calculator
		
		p.add(dot);
		p.add(equal);
		p.add(clear);
		p.add(plusMinus);
		p.add(c);
		p.add(backspace);
		p.add(upArrow);

		// adding operations
		
		p.add(plus);
		p.add(minus);
		p.add(divide);
		p.add(multiply);
		p.add(mod);
		p.add(p1);
		p.add(p2);
		
		// adding main display 
		
		p.add(tripleBar);
		p.add(display);
		p.add(Prog);
		p.add(equation);
		
		// adding binary things
		
		p.add(HEX);
		p.add(OCT);
		p.add(DEC);
		p.add(BIN);
		
		// HEX Letter keys
		
		p.add(letterA);
		p.add(letterB);
		p.add(letterC);
		p.add(letterD);
		p.add(letterD);
		p.add(letterE);
		p.add(letterF);
		
		// non usable just for display buttons
		
		p.add(rsh);
		p.add(lsh);
		p.add(xor);
		p.add(not);
		p.add(or);
		p.add(and);
		p.add(menu1);
		p.add(menu2);
		p.add(word);
		p.add(ms);
		p.add(m);
		
		// adding radio buttons
		
		p.add(hex);
		p.add(dec);
		p.add(bin);
		p.add(oct);
		
		// content pane
		
		getContentPane().add(p);
		setDefaultCloseOperation(3);
		setSize(302, 500);
		setVisible(true);
		
		// adding action listener to make the functional buttons functional
		
		equal.addActionListener(this);
		zero.addActionListener(this);
		one.addActionListener(this);
		two.addActionListener(this);
		three.addActionListener(this);
		four.addActionListener(this);
		five.addActionListener(this);
		six.addActionListener(this);
		seven.addActionListener(this);
		eight.addActionListener(this);
		nine.addActionListener(this);
		plus.addActionListener(this);
		minus.addActionListener(this);
		multiply.addActionListener(this);
		divide.addActionListener(this);
		mod.addActionListener(this);
		p1.addActionListener(this);
		p2.addActionListener(this);
		plusMinus.addActionListener(this);
		clear.addActionListener(this);
		c.addActionListener(this);
		backspace.addActionListener(this);
		hex.addActionListener(this);
		dec.addActionListener(this);
		bin.addActionListener(this);
		oct.addActionListener(this);
	}
	
	// this happens whenever a button is clicked and then using if else statements
	// it is checked what button is clicked

	public void actionPerformed(ActionEvent e) 
	{
		// these are all the numbers buttons
		
		if(e.getSource() == zero)
		{
			if(Continue)
			{
				input = 0;
				Continue = false;
			}
			input = (input * 10);
		}
		if(e.getSource() == one)
		{
			if(Continue)
			{
				input = 0;
				Continue = false;
			}
			input = ((input * 10) + 1);
		}
		if(e.getSource() == two)
		{
			if(Continue)
			{
				input = 0;
				Continue = false;
			}
			input = ((input * 10) + 2);
		}
		if(e.getSource() == three)
		{
			if(Continue)
			{
				input = 0;
				Continue = false;
			}
			input = ((input * 10) + 3);
		}
		if(e.getSource() == four)
		{
			if(Continue)
			{
				input = 0;
				Continue = false;
			}
			input = ((input * 10) + 4);
		}
		if(e.getSource() == five)
		{
			if(Continue)
			{
				input = 0;
				Continue = false;
			}
			input = ((input * 10) + 5);
		}
		if(e.getSource() == six)
		{
			if(Continue)
			{
				input = 0;
				Continue = false;
			}
			input = ((input * 10) + 6);
		}
		if(e.getSource() == seven)
		{
			if(Continue)
			{
				input = 0;
				Continue = false;
			}
			input = ((input * 10) + 7);
		}
		if(e.getSource() == eight)
		{
			if(Continue)
			{
				input = 0;
				Continue = false;
			}
			input = ((input * 10) + 8);
		}
		if(e.getSource() == nine)
		{
			if(Continue)
			{
				input = 0;
				Continue = false;
			}
			input = ((input * 10) + 9);
		}
		
		// these are all the clear buttons 
		
		if(e.getSource() == clear)
		{
			if (!ceRepeat)
			{
				int length = 0;
				length = (int) (Math.log10(input) + 1);
				userInput = userInput.substring(0, userInput.length() - length);
				input = 0;
				ceRepeat = true;
			}
			
		}
		else
		{
			ceRepeat = false;
		}
		
		if(e.getSource() == c)
		{
			input = 0;
			userInput = "";
		}
		
		// these are operation buttons, they perform operations on the numbers
		
		if(e.getSource() == plus)
		{
		
			if(!oRepeat)
			{
				if(!P2)
				{
					userInput += Integer.toString(input);
				}
	
				input = 0;
				userInput += " + ";
				oRepeat = true;
			}
			
		}
		else if(e.getSource() == minus)
		{
			if(!oRepeat)
			{
				if(!P2)
				{
					userInput += Integer.toString(input);
				}
				
				input = 0;
				userInput += " - ";
				oRepeat = true;
			}
		}
		else if(e.getSource() == multiply)
		{
			if(!oRepeat)
			{
				if(!P2)
				{
					userInput += Integer.toString(input);
				}

				input = 0;
				userInput += " * ";
				oRepeat = true;
			}
		}
		else if(e.getSource() == divide)
		{
			if(!oRepeat)
			{
				if(!P2)
				{
					userInput += Integer.toString(input);
				}
				
				input = 0;
				userInput += " / ";
				oRepeat = true;
			}
		}
		else if(e.getSource() == mod)
		{
			if(!oRepeat)
			{
				if(!P2)
				{
					userInput += Integer.toString(input);
				}
				
				input = 0;
				userInput += " % ";
				oRepeat = true;
				
			}
		}
		
		// here are the parenthesis
		else if(e.getSource() == p1)
		{
			if(!oRepeat)
			{
				userInput += Integer.toString(input);
			}
			
			userInput += " ( ";
			leftParanthesis++;
			P2 = true;
			input = 0;
		}
		else if(e.getSource() == p2)
		{
			
			if(leftParanthesis > rightParanthesis)
			{
				userInput += Integer.toString(input);
				userInput += " )";
				rightParanthesis++;
				P2 = true;
			}

		}
		else
		{
			oRepeat = false;
			P2 = false;
		}
		
		// backspace to take out one at a time
		
		if(e.getSource() == backspace)
		{
			input = input / 10;
			
			if (input != 0 && userInput != "")
			{
				userInput = userInput.substring(0, userInput.length() - 1);
		    }
			
		}
		
		// negate button
		
		if(e.getSource() == plusMinus)
		{
			input = input * -1;
		}
		
		// all the hex dec bin oct radio functionality. Only one selected at a time
		
		if(e.getSource() == hex)
		{
			hex.setSelected(true);
			bin.setSelected(false);
			oct.setSelected(false);
			dec.setSelected(false);
		}
		
		if(e.getSource() == bin)
		{
			bin.setSelected(true);
			hex.setSelected(false);
			oct.setSelected(false);
			dec.setSelected(false);
		}
		
		if(e.getSource() == oct)
		{
			oct.setSelected(true);
			bin.setSelected(false);
			hex.setSelected(false);
			dec.setSelected(false);
		}
		
		if(e.getSource() == dec)
		{
			dec.setSelected(true);
			bin.setSelected(false);
			oct.setSelected(false);
			hex.setSelected(false);
		}
		
		// This is the equal button. all the end things happen here
		// Checks the radio buttons and displays the answer accordingly
		// everything is set back to 0 for it to run again
		// everything is reset
		// end of operations here
		
		if(e.getSource() == equal)
		{
			userInput += Integer.toString(input);
			Continue = true;		
			
			if(leftParanthesis > rightParanthesis)
			{
				for(int i = rightParanthesis; i < leftParanthesis; i++)
				{
					userInput += " )";
				}
				
			}
			
			if(!(userInput.substring(userInput.length() - 1) == "+" && userInput.substring(userInput.length() - 1) == "-" && userInput.substring(userInput.length() - 1) == "*" && userInput.substring(userInput.length() - 1) == "/"))
			{
				answer = postfixEvaluate(shuntingYard(userInput));
			}
			
			// Displaying HEX,DEC,BIN,OCT.
			HEX.setText("HEX " + Integer.toHexString(answer).toUpperCase());
			DEC.setText("DEC " + answer);
			BIN.setText("BIN " + Integer.toBinaryString(answer));
			OCT.setText("OCT " + Integer.toOctalString(answer));
			
			if(hex.isSelected())
			{
				display.setText(Integer.toHexString(answer).toUpperCase());
			}
			else if (bin.isSelected())
			{
				display.setText(Integer.toBinaryString(answer));
			}
			else if (oct.isSelected())
			{
				display.setText(Integer.toOctalString(answer));
			}
			else
			{
				display.setText(Integer.toString(answer));
			}
			
			input = answer;
			
			userInput = "";
			equation.setText(userInput);
			
		}
		else
		{
			// if equal is not hit, calculator resumes normal functions
			
			if(hex.isSelected())
			{
				display.setText(Integer.toHexString(input).toUpperCase());
			}
			else if (bin.isSelected())
			{
				display.setText(Integer.toBinaryString(input));
			}
			else if (oct.isSelected())
			{
				display.setText(Integer.toOctalString(input));
			}
			else
			{
				display.setText(Integer.toString(input));
			}
			
			equation.setText(userInput);
			
			// Displaying HEX,DEC,BIN,OCT.
			
			HEX.setText("HEX " + Integer.toHexString(input).toUpperCase());
			DEC.setText("DEC " + input);
			BIN.setText("BIN " + Integer.toBinaryString(input));
			OCT.setText("OCT " + Integer.toOctalString(input));
		}
				
		
	}

	// Mouse functions
	
	public void mouseClicked(MouseEvent e)
	{
					
	}
	
	public void mouseEntered(MouseEvent e) 
	{
			
	}
	
	public void mouseExited(MouseEvent e)
	{
		
	}
	
	public void mousePressed(MouseEvent e) 
	{
		
	}
	
	public void mouseReleased(MouseEvent e) 
	{
		
	}
	
	// function to evaluate the shunting yard algorithm output
	// takes the postfix and performs the operations accordingly
	// used when equal is hit for final answer
	
	public static int postfixEvaluate(String user)
	{
		
		// stack to perform
		
	 	Stack<Integer> s = new Stack<Integer> ();
		Scanner tokens = new Scanner(user);
		
		// while stack isnt empty it keeps going
		
		while (tokens.hasNext()) 
		{
			if (tokens.hasNextInt())
			{
				s.push(tokens.nextInt());
			}
			else 
			{
				int num2 = s.pop();
				int num1 = s.pop();
				String op = tokens.next();
				
				// operations are performed accordingly
				
				if (op.equals("+")) 
				{
					s.push(num1 + num2);
				} 
				else if (op.equals("-")) 
				{
					s.push(num1 - num2);
				} 
				else if (op.equals("*")) 
				{
					s.push(num1 * num2);
				} 
				else if (op.equals("%"))
				{
					s.push(num1 % num2);
				}	
				else 
				{
					s.push(num1 / num2);
				}
				
			//  order: "+", "-", "*", "/", "%"
				
			}
		}
		
		// closing scanner
		
		tokens.close();
		
		// returning calculation of the postfix
		
		return s.pop();
		
    }
    
    static String shuntingYard(String conv) 
    {
        /* checking token
           dividing by 2
           getting either 0, 0, 1, 1, 1, 2 
        */
    	
    	// This string is to dictate precedence
    	
        final String ops = "-+%/*^";
 
        // this is a string builder
        
        StringBuilder sb = new StringBuilder();
        
        // this is a stack to store
        
        Stack<Integer> s = new Stack<>();
 
        // storing accordingly
        
        for (String token : conv.split("\\s")) 
        {
            if (token.isEmpty())
                continue;
            char c = token.charAt(0);
            int idx = ops.indexOf(c);
 
            // checking  for operator
            
            if (idx != -1) 
            {
                if (s.isEmpty())
                    s.push(idx);
 
                else 
                {
                    while (!s.isEmpty()) 
                    {
                        int prec2 = s.peek() / 2;
                        int prec1 = idx / 2;
                        
                        if (prec2 > prec1 || (prec2 == prec1 && c != '^'))
                        {
                    		sb.append(ops.charAt(s.pop())).append(' ');
                        }
                        else 
                    	{
                    		break;
                    	}
                    }
                    s.push(idx);
                }
            } 
            
            // -2 is (
            
            // checking parenthesis
            
            else if (c == '(') 
            {
                s.push(-2);
            } 
            else if (c == ')') 
            {
                
                while (s.peek() != -2)
                    sb.append(ops.charAt(s.pop())).append(' ');
                s.pop();
            }
            else 
            {
                sb.append(token).append(' ');
            }
        }
        while (!s.isEmpty())
        {
        	sb.append(ops.charAt(s.pop())).append(' ');
        }
            
        // returning the postfix string
        
        return sb.toString();
    }
	
    
    
}