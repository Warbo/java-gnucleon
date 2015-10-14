import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gnucleon extends JPanel
{

   /**
    * Default constructor
    */
   public Gnucleon()
   {
   	//jpanel1 = new JPanel();
   	//cc = new CellConstraints();
   	/*widthLabel = new JLabel("Width:");
   	heightLabel = new JLabel("Height:");
   	widthEntry = new JFormattedTextField();
   	heightEntry = new JFormattedTextField();
   	playersLabel = new JLabel("Players:");
   	playerEntry = new JFormattedTextField();
   	startButton = new JButton("Start");
   	startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				getValues();
				setupBoard();
			}
		});*/
   	width = 12;
   	height = 10;
   	players = 2;
   	setupBoard();
   	currentPlayer = 1;
      initializePanel();
   }

	/*private void getValues() {
		width = Integer.parseInt(widthEntry.getText());
		height = Integer.parseInt(heightEntry.getText());
		players = Integer.parseInt(playerEntry.getText());
	}*/

	private void setupBoard() {
		values = new int[width][height];
		limits = new int[width][height];
		owners = new int[width][height];
		buttons = new JButton[width][height];
		createPanel();
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				values[i][j] = 0;
				owners[i][j] = 0;
				limits[i][j] = 4;
				//makeButton(i, j);
				//jpanel1.add(buttons[i][j], cc.xy(i+2,j+2));
			}
			limits[i][0] = 3;
			limits[i][height-1] = 3;
		}
		//jpanel1.add(playersLabel, cc.xy(2, height+2));
		//jpanel1.add(playerEntry, cc.xy(3, height+2));
		//jpanel1.add(widthLabel, cc.xy(4, height+2));
		//jpanel1.add(widthEntry, cc.xy(5, height+2));
		//jpanel1.add(heightLabel, cc.xy(6, height+2));
		//jpanel1.add(heightEntry, cc.xy(7, height+2));
		//jpanel1.add(startButton, cc.xy(8, height+2));
		for (int i=0; i<height; i++) {
			limits[0][i] = 3;
			limits[width-1][i] = 3;
		}
		limits[0][0] = 2;
		limits[0][height-1] = 2;
		limits[width-1][0] = 2;
		limits[width-1][height-1] = 2;
	}

   /**
    * Main method for panel
    */
   public static void main(String[] args)
   {
   	  switch (args.length)
   	  {
   	  	case 0: playerNumber = 2;
   	  	break;
   	  	case 1: playerNumber = args[0];
   	  	break;
   	  	default:
   	  	playerNumber = 2;
   	  }


      JFrame frame = new JFrame();
      frame.setSize(535, 290);
      frame.setLocation(100, 100);
      frame.getContentPane().add(new Gnucleon());
      frame.setVisible(true);

      frame.addWindowListener( new WindowAdapter()
      {
         public void windowClosing( WindowEvent evt )
         {
            System.exit(0);
         }
      });
   }

   /**
    * Adds fill components to empty cells in the first row and first column of the grid.
    * This ensures that the grid spacing will be the same as shown in the designer.
    * @param cols an array of column indices in the first row where fill components should be added.
    * @param rows an array of row indices in the first column where fill components should be added.
    */
   void addFillComponents( Container panel, int[] cols, int[] rows )
   {
      Dimension filler = new Dimension(10,10);

      boolean filled_cell_11 = false;
      //CellConstraints cc = new CellConstraints();
      if ( cols.length > 0 && rows.length > 0 )
      {
         if ( cols[0] == 1 && rows[0] == 1 )
         {
            /** add a rigid area  */
            panel.add( Box.createRigidArea( filler ), cc.xy(1,1) );
            filled_cell_11 = true;
         }
      }

      for( int index = 0; index < cols.length; index++ )
      {
         if ( cols[index] == 1 && filled_cell_11 )
         {
            continue;
         }
         panel.add( Box.createRigidArea( filler ), cc.xy(cols[index],1) );
      }

      for( int index = 0; index < rows.length; index++ )
      {
         if ( rows[index] == 1 && filled_cell_11 )
         {
            continue;
         }
         panel.add( Box.createRigidArea( filler ), cc.xy(1,rows[index]) );
      }

   }

   /**
    * Helper method to load an image file from the CLASSPATH
    * @param imageName the package and name of the file to load relative to the CLASSPATH
    * @return an ImageIcon instance with the specified image file
    * @throws IllegalArgumentException if the image resource cannot be loaded.
    */
   public ImageIcon loadImage( String imageName )
   {
      try
      {
         ClassLoader classloader = getClass().getClassLoader();
         java.net.URL url = classloader.getResource( imageName );
         if ( url != null )
         {
            ImageIcon icon = new ImageIcon( url );
            return icon;
         }
      }
      catch( Exception e )
      {
         e.printStackTrace();
      }
      throw new IllegalArgumentException( "Unable to load image: " + imageName );
   }

   public JPanel createPanel()
   {
      JPanel jpanel1 = new JPanel();
      FormLayout formlayout1 = new FormLayout("FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE,FILL:DEFAULT:NONE","CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE");
      cc = new CellConstraints();
      jpanel1.setLayout(formlayout1);

	buttons = new JButton[12][10];
	for (i=0; i<12; i++) {
		for (j=0; j<10; j++) {
			makeButton(i, j);
			jpanel1.add(buttons[i][j], cc.xy(i+2,j+2));
		}
	}
	//jpanel1.add(playersLabel, cc.xy(2, height+2));
	//jpanel1.add(playerEntry, cc.xy(3, height+2));
	//jpanel1.add(widthLabel, cc.xy(4, height+2));
	//jpanel1.add(widthEntry, cc.xy(5, height+2));
	//jpanel1.add(heightLabel, cc.xy(6, height+2));
	//jpanel1.add(heightEntry, cc.xy(7, height+2));
	//jpanel1.add(startButton, cc.xy(8, height+2));

      addFillComponents(jpanel1,new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14 },new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12 });
      return jpanel1;
   }

   /**
    * Initializer
    */
   protected void initializePanel()
   {
      setLayout(new BorderLayout());
      add(createPanel(), BorderLayout.CENTER);
   }

	private void play(int x, int y, boolean pwn) {
		if ((!pwn && owners[x][y] == 0) || (!pwn && owners[x][y] == currentPlayer) || pwn) {
			owners[x][y] = currentPlayer;
			values[x][y]++;
			buttons[x][y].setText("" + values[x][y]);
			if (values[x][y] >= limits[x][y]) {
				System.out.println("Bang");
				explode(x, y);
				if (values[x][y] == 0) {
					owners[x][y] = 0;
					buttons[x][y].setBackground(Color.white);
				}
			}
			buttons[x][y].setText("" + values[x][y]);
			if (values[x][y] != 0) {
				switch (currentPlayer) {
					case 1:
						buttons[x][y].setBackground(Color.blue);
						break;
					case 2:
						buttons[x][y].setBackground(Color.green);
						break;
					case 3:
						buttons[x][y].setBackground(Color.red);
						break;
					case 4:
						buttons[x][y].setBackground(Color.magenta);
						break;
					case 5:
						buttons[x][y].setBackground(Color.yellow);
						break;
					case 6:
						buttons[x][y].setBackground(Color.cyan);
						break;
					case 7:
						buttons[x][y].setBackground(Color.orange);
						break;
					case 8:
						buttons[x][y].setBackground(Color.gray);
						break;
					case 9:
						buttons[x][y].setBackground(Color.pink);
						break;
				}
			}
			if (!pwn) {
				if (currentPlayer >= players) {
					currentPlayer = 1;
				}
				else {
					currentPlayer++;
				}
			}
		}

	}

	private void explode(int x, int y) {
		boolean up = true;
		boolean down = true;
		boolean left = true;
		boolean right = true;
		while (values[x][y] >= limits[x][y]) {
			values[x][y] = values[x][y] - limits[x][y];
			if (x == 0) {
				left = false;
			}
			if (x == 11) {
				right = false;
			}
			if (y == 0) {
				up = false;
			}
			if (y == 9) {
				down = false;
			}
			if (up) {
				play(x, y-1, true);
			}
			if (down) {
				play(x, y+1, true);
			}
			if (left) {
				play(x-1, y, true);
			}
			if (right) {
				play(x+1, y, true);
			}
		}
	}

	private int[][] limits;
	private int[][] values;
	private int[][] owners;
	private int givenPlayers;
	private int width;
	private int height;
	private int players;
	private int currentPlayer;

	private JButton[][] buttons;
	private JButton startButton;
	private JFormattedTextField playerEntry;
	private JFormattedTextField widthEntry;
	private JFormattedTextField heightEntry;
	private JLabel playersLabel;
	private JLabel widthLabel;
	private JLabel heightLabel;
	private int i;
	private int j;
	private CellConstraints cc;
	//private JPanel jpanel1;

	private void makeButton(final int i, final int j) {
		buttons[i][j] = new JButton();
		buttons[i][j].setActionCommand("JButton");
		buttons[i][j].setText("0");
		buttons[i][j].setBackground(Color.white);
		buttons[i][j].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				play(i, j, false);
			}
		});
	}

}
