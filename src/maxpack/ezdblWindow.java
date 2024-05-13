package maxpack;

import java.awt.EventQueue;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.proteanit.sql.DbUtils;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JEditorPane;
import javax.swing.JLayeredPane;
import javax.swing.SpringLayout;

public class ezdblWindow {

	private JFrame frame;
	private JTable table;
	private JTextField Semester1G;
	private JTextField Semester2G;
	private JTextField Name;
	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	private DefaultTableModel model = new DefaultTableModel();
	private JTextField Weight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ezdblWindow window = new ezdblWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ezdblWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(50, 50, 1050, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("ErMax");
		lblNewLabel.setFont(new Font("Yu Gothic Light", Font.PLAIN, 42));
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Name.setText((String)table.getValueAt(table.getSelectedRow(), 0));
				Semester1G.setText((String)table.getValueAt(table.getSelectedRow(), 1));
				Semester2G.setText((String)table.getValueAt(table.getSelectedRow(), 2));
				Weight.setText((String)table.getValueAt(table.getSelectedRow(), 3));
			}
		});
		scrollPane.setViewportView(table);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 73, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 494, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 381, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 1024, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 18, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 339, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, 87, SpringLayout.NORTH, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 59, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 491, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 388, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 1034, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panel_1);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(scrollPane);
		panel_1.setVisible(false);
		
		
		JLabel lblGpa = new JLabel("GPA");
		springLayout.putConstraint(SpringLayout.NORTH, lblGpa, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblGpa, 455, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblGpa, 69, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblGpa, 529, SpringLayout.WEST, frame.getContentPane());
		lblGpa.setFont(new Font("DengXian", Font.PLAIN, 32));
		frame.getContentPane().add(lblGpa);
		
		JLabel lblEzdbl = new JLabel("ezdbl");
		springLayout.putConstraint(SpringLayout.NORTH, lblEzdbl, 34, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblEzdbl, 518, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblEzdbl, 63, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblEzdbl, 569, SpringLayout.WEST, frame.getContentPane());
		lblEzdbl.setFont(new Font("Dialog", Font.PLAIN, 18));
		frame.getContentPane().add(lblEzdbl);
		
		JButton btnNewButton = new JButton("Clear");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 392, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 504, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 593, SpringLayout.WEST, frame.getContentPane());
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Name.setText(null);
				Semester1G.setText(null);
				Semester2G.setText(null);
				Weight.setText(null);
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnAdd = new JButton("Add");
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 392, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnAdd, 656, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnAdd, 745, SpringLayout.WEST, frame.getContentPane());
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = (DefaultTableModel) table.getModel();
				
				try {
					String CourseName = Name.getText();
					String Sem1 = Semester1G.getText();
					String Sem2 = Semester2G.getText();
					String weight = Weight.getText();
					//int grade = Integer.parseInt(txtFinalGrade.getText());
					boolean a = true;
					for (int x=0; x<table.getRowCount(); x++) {
						if (((String)table.getValueAt(x, 0)).equals(CourseName)) {
							a = false;
							//throw new ArithmeticException("simmilar value found in key, cannot hash");
						}
					}
					if (a) {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					con = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\k1326699\\OneDrive - Katy Independent School District\\10th Grade\\CS III\\DataBase\\StudentJavaDatabase\\ErMax_GPAezdbl\\src\\GPA.accdb");
					st = con.createStatement();
					st.executeUpdate(String.format("insert into GPA (CourseName,Semester1,Semester2,weight) values('"+CourseName+"','"+Sem1+"','"+Sem2+"','"+weight+"')"));
					String [] r = {(String)Name.getText(), (String)Semester1G.getText(), (String)Semester2G.getText(), (String)Weight.getText()};
					model.addRow(r);
						}
					else {JOptionPane.showMessageDialog(null, "error, you cannot have more than one ID");}
				}
					
				 catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
						
			}});
		frame.getContentPane().add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		springLayout.putConstraint(SpringLayout.NORTH, btnDelete, 392, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnDelete, 792, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnDelete, 881, SpringLayout.WEST, frame.getContentPane());
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String cell = (String)table.getValueAt(row, 0);
				model = (DefaultTableModel) table.getModel();
				model.removeRow(row);
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					con = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\k1326699\\OneDrive - Katy Independent School District\\10th Grade\\CS III\\DataBase\\StudentJavaDatabase\\ErMax_GPAezdbl\\src\\GPA.accdb");
				    st = con.createStatement();
				    st.executeUpdate("delete from GPA where CourseName='"+cell+"'");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		springLayout.putConstraint(SpringLayout.NORTH, btnUpdate, 392, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnUpdate, 923, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnUpdate, 1012, SpringLayout.WEST, frame.getContentPane());
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String CourseName = Name.getText();
				String Sem1 = Semester1G.getText();
				String Sem2 = Semester2G.getText();
				String weight = Weight.getText();
				
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					con = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\k1326699\\OneDrive - Katy Independent School District\\10th Grade\\CS III\\DataBase\\StudentJavaDatabase\\ErMax_GPAezdbl\\src\\GPA.accdb");
					pst = con.prepareStatement("update GPA set CourseName='"+CourseName+"', Semester1='"+Sem1+"', Semester2='"+Sem2+"', weight='"+weight+"' where CourseName= '"+table.getValueAt(row, 0)+"'");
					pst.executeUpdate();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				table.setValueAt(CourseName, row, 0);
				table.setValueAt(Sem1, row, 1);
				table.setValueAt(Sem2, row, 2);
				table.setValueAt(weight, row, 3);
			}
		});
		frame.getContentPane().add(btnUpdate);
		
		JLabel lblNewLabel_1 = new JLabel("Course Name:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 99, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 56, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 128, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, 154, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Semester 1 Grade");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1, 139, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1_1, 56, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1_1, 168, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1_1, 188, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Semester 2 Grade:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1_2, 179, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1_2, 56, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1_2, 208, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1_2, 188, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel_1_2);
		
		Semester1G = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, Semester1G, 145, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, Semester1G, 197, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, Semester1G, 321, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(Semester1G);
		Semester1G.setColumns(10);
		
		Semester2G = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, Semester2G, 185, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, Semester2G, 198, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, Semester2G, 322, SpringLayout.WEST, frame.getContentPane());
		Semester2G.setColumns(10);
		frame.getContentPane().add(Semester2G);
		
		Name = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, Name, 104, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, Name, 197, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, Name, 124, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, Name, 321, SpringLayout.WEST, frame.getContentPane());
		Name.setColumns(10);
		frame.getContentPane().add(Name);
		
		//Loading the DB
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			con = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\k1326699\\OneDrive - Katy Independent School District\\10th Grade\\CS III\\DataBase\\StudentJavaDatabase\\ErMax_GPAezdbl\\src\\GPA.accdb");
		    //JOptionPane.showMessageDialog(null, "Connection successful.");
		    String sql = "select CourseName, Semester1, Semester2, Weight from GPA";
		    st = con.createStatement();
		    rs = st.executeQuery(sql);
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (ClassNotFoundException | SQLException adfssdafadsfasfdasdfasdfasdfasdfasdfasdfasdfasdfsadfasdfasdfasdfadsfsadfwqertqwerweqrqwerewq) {
			// TODO Auto-generated catch block
			adfssdafadsfasfdasdfasdfasdfasdfasdfasdfasdfasdfsadfasdfasdfasdfadsfsadfwqertqwerweqrqwerewq.printStackTrace();
		}
		
		
		JButton btnNewButton_1 = new JButton("Reload From DB");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1, 93, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 344, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_1, 137, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1, 484, SpringLayout.WEST, frame.getContentPane());
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					con = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\k1326699\\OneDrive - Katy Independent School District\\10th Grade\\CS III\\DataBase\\StudentJavaDatabase\\ErMax_GPAezdbl\\src\\GPA.accdb");
				    //JOptionPane.showMessageDialog(null, "Connection successful.");
				    String sql = "select CourseName, Semester1, Semester2, Weight from GPA";
				    st = con.createStatement();
				    rs = st.executeQuery(sql);
				    table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (ClassNotFoundException | SQLException adfssdafadsfasfdasdfasdfasdfasdfasdfasdfasdfasdfsadfasdfasdfasdfadsfsadfwqertqwerweqrqwerewq) {
					// TODO Auto-generated catch block
					adfssdafadsfasfdasdfasdfasdfasdfasdfasdfasdfasdfsadfasdfasdfasdfadsfsadfwqertqwerweqrqwerewq.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("GPA Weight:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1_2_1, 219, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1_2_1, 56, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1_2_1, 248, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1_2_1, 188, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		Weight = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, Weight, 225, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, Weight, 198, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, Weight, 322, SpringLayout.WEST, frame.getContentPane());
		Weight.setColumns(10);
		frame.getContentPane().add(Weight);
		
		JLabel lblNewLabel_2 = new JLabel("Unweighted GPA:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 269, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 56, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, 317, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2, 188, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setForeground(Color.BLACK);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Weighted GPA:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2_1, 269, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2_1, 309, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_2_1, 317, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2_1, 441, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 269, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 42, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 395, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 452, SpringLayout.WEST, frame.getContentPane());
		panel.setBorder(UIManager.getBorder("TextArea.border"));
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JEditorPane idlo = new JEditorPane();
		idlo.setToolTipText("Hello");
		idlo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		idlo.setForeground(new Color(0, 0, 0));
		idlo.setText("-");
		idlo.setBounds(83, 40, 99, 35);
		panel.add(idlo);
		
		JEditorPane idow = new JEditorPane();
		idow.setToolTipText("Hello");
		idow.setText("-");
		idow.setForeground(new Color(0, 0, 0));
		idow.setFont(new Font("Tahoma", Font.PLAIN, 24));
		idow.setBounds(301, 40, 99, 35);
		panel.add(idow);
		
		JButton btnNewButton_1_1 = new JButton("Calculate");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1_1, 411, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1_1, 42, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_1_1, 455, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1_1, 239, SpringLayout.WEST, frame.getContentPane());
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double Grade = 0;
				double GradeUWAI = 0;
				for (int x=0; x<table.getRowCount(); x++){
					double v1 = Double.parseDouble((String)table.getValueAt(x, 1));
					double v2 = Double.parseDouble((String)table.getValueAt(x, 2));
					double v1u = Double.parseDouble((String)table.getValueAt(x, 1));
					double v2u = Double.parseDouble((String)table.getValueAt(x, 2));
					
					if (v1>=90) {
						v1=5;
						v1u=4;
					}
					else if (v1>=80) {
						v1=4;
						v1u=3;
					}
					else if (v1>=70) {
						v1=3;
						v1u=2;
					}
					if (v2>=90) {
						v2=5;
						v2u=4;
					}
					else if (v2>=80) {
						v2=4;
						v2u=3;
					}
					else if (v2>=70) {
						v2=3;
						v2u=2;
					}
					//double avg = v1+v2;
					v1= v1+Integer.parseInt((String)table.getValueAt(x, 3))-5;
					v2= v2+Integer.parseInt((String)table.getValueAt(x, 3))-5;
					double avg = (v1+v2)/2;
					double avgu = (v1u+v2u)/2;
					System.out.print(avg+" "+v1u+v2u);
					System.out.println((String)table.getValueAt(x, 0));
					//int grade = Integer.parseInt((String) table.getValueAt(x, 3));
					//int gradeu = 4;
					Grade+=avg;
					GradeUWAI += avgu;
				}
				System.out.println("sdfas"+Grade);
				System.out.println(GradeUWAI);
				idlo.setText(Double.toString(Double.parseDouble(Double.toString(Grade))/table.getRowCount()));
				if (Double.parseDouble(idlo.getText())>4) {idlo.setForeground(new Color(34, 139, 34));}
				else if (Double.parseDouble(idlo.getText())>3) {idlo.setForeground(new Color(255, 204, 0));}
				else {idlo.setForeground(Color.red);}
				idow.setText(Double.toString(Double.parseDouble(Double.toString(GradeUWAI))/table.getRowCount()));
				if (Double.parseDouble(idow.getText())>3.5) {idow.setForeground(new Color(34, 139, 34));}
				else if (Double.parseDouble(idow.getText())>3) {idow.setForeground(new Color(255, 204, 0));}
				else {idow.setForeground(Color.red);}
			}
		});
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Clear");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1_1_1, 411, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1_1_1, 255, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_1_1_1, 455, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1_1_1, 452, SpringLayout.WEST, frame.getContentPane());
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idlo.setText("-");
				idlo.setForeground(Color.black);
				idow.setText("-");
				idow.setForeground(Color.black);
			}
		});
		frame.getContentPane().add(btnNewButton_1_1_1);
		
		JButton btnAdd_1 = new JButton("Hide/Unhide");
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd_1, 426, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnAdd_1, 656, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnAdd_1, 470, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnAdd_1, 879, SpringLayout.WEST, frame.getContentPane());
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!panel_1.isVisible()) {
					panel_1.setVisible(true);
				}
				else {
					int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to show your grades?", "Warning", JOptionPane.YES_NO_OPTION);
					if (a == JOptionPane.YES_OPTION) {
						panel_1.setVisible(false);
					}
				}
				}
		});
		frame.getContentPane().add(btnAdd_1);
	}
}
