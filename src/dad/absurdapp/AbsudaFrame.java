package dad.absurdapp;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class AbsudaFrame extends JFrame {
	private JSplitPane divisionPanel;
	private JPanel FicherosPanel;
	private JPanel ColorPanel;
	private JList<String> ficherosList;
	private DefaultListModel<String> ficherosModel;
	private JButton anadirButton;
	private JButton colorButton;
	private FileDialog fileChooser;

	private JSlider rSlider;
	private JSlider gSlider;
	private JSlider bSlider;
	private JLabel rLabel;
	private JLabel gLabel;
	private JLabel bLabel;
	// componente Jslider
	static final int colorMin = 0;
	static final int colorMax = 255;
	static final int colorInit = 0;
	private int rojo;
	private int verde;
	private int azul;

	public AbsudaFrame() throws IOException {
		initFrame();
		initComponent();
	}

	private void initFrame() {
		setTitle("AbsurdaAPP");
		setSize(480, 640);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void initComponent() throws IOException {
		// lista donde se van a contener los datos que se mostraran
		ficherosModel = new DefaultListModel<String>();
		// estas dos linas hacen lo mismo
		// ficherosList.setModel(ficherosModel);
		ficherosList = new JList<String>(ficherosModel);
		ficherosList.setBounds(20, 20, 400, 200);

		JScrollPane ficherosScroll = new JScrollPane(ficherosList);
		ficherosScroll.setBounds(20, 20, 400, 200);

		ficherosModel.addElement("Manuel.txt");
		ficherosModel.addElement("Juanito.txt");
		ficherosModel.addElement("Pepito.txt");
		

		anadirButton = new JButton("añadir");
		anadirButton.setBounds(200, 250, 150, 50);
		anadirButton.setIcon(new ImageIcon("disquete.png"));
		anadirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAnadirButtonActionPerformed(e);
			}
		});

		fileChooser = new FileDialog(this);
		FicherosPanel = new JPanel();
		FicherosPanel.setBorder(BorderFactory.createTitledBorder("Ficheros"));
		FicherosPanel.setBackground(Color.RED);
		FicherosPanel.setLayout(null);
		FicherosPanel.add(ficherosScroll);
		FicherosPanel.add(anadirButton);

		ColorPanel = new JPanel();
		ColorPanel.setBorder(BorderFactory.createTitledBorder("Colores"));
		ColorPanel.setLayout(null);

		rSlider = new JSlider(JSlider.HORIZONTAL, colorMin, colorMax, colorInit);
		rSlider.setBounds(100, 50, 300, 70);
		rSlider.setPaintTicks(true);
		rSlider.setPaintLabels(true);
		rSlider.setMajorTickSpacing(25);
		rSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				SlideronStateChanged(e);
			}
		});
		rLabel = new JLabel("Componente roja");
		rLabel.setBounds(20, 10, 150, 70);
		ColorPanel.add(rSlider);
		ColorPanel.add(rLabel);

		gSlider = new JSlider(JSlider.HORIZONTAL, colorMin, colorMax, colorInit);
		gSlider.setBounds(100, 120, 300, 70);
		gSlider.setPaintTicks(true);
		gSlider.setMajorTickSpacing(25);
		gSlider.setPaintLabels(true);
		gLabel = new JLabel("Componente verde");
		gLabel.setBounds(20, 70, 150, 90);
		ColorPanel.add(gLabel);
		ColorPanel.add(gSlider);
		gSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				SlideronStateChanged(e);
			}
		});

		bSlider = new JSlider(JSlider.HORIZONTAL, colorMin, colorMax, colorInit);
		bSlider.setBounds(100, 200, 300, 70);
		bSlider.setPaintTicks(true);
		bSlider.setMajorTickSpacing(25);
		bSlider.setPaintLabels(true);
		bLabel = new JLabel("Componente azul");
		bLabel.setBounds(20, 140, 150, 110);
		ColorPanel.add(bSlider);
		ColorPanel.add(bLabel);
		bSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				SlideronStateChanged(e);
			}
		});
		colorButton = new JButton("Elegir color");
		colorButton.setBounds(300, 270, 100, 25);
		colorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onColorButtonActionPerformed(e);
			}
		});
		ColorPanel.add(colorButton);
		Color color1 = ColorPanel.getBackground();
		
		rSlider.setValue(color1.getRed());
		gSlider.setValue(color1.getGreen());
		bSlider.setValue(color1.getBlue());
		
		
		
		divisionPanel = new JSplitPane();
		divisionPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		divisionPanel.setLeftComponent(FicherosPanel);
		divisionPanel.setRightComponent(ColorPanel);
		divisionPanel.setDividerLocation(300);
		divisionPanel.setOneTouchExpandable(true);

		getContentPane().add(divisionPanel);
	}

	protected void onColorButtonActionPerformed(ActionEvent e) {
		Color color =JColorChooser.showDialog(this, "Elija un color", getBackground());
		if (color != null) {
			ColorPanel.setBackground(color);
			rSlider.setValue(color.getRed());
			gSlider.setValue(color.getGreen());
			bSlider.setValue(color.getBlue());
		}
	}

	protected void SlideronStateChanged(ChangeEvent e) {
		rojo = rSlider.getValue();
		verde = gSlider.getValue();
		azul = bSlider.getValue();
		ColorPanel.setBackground(new Color(rojo, verde, azul));
	}

	protected void onAnadirButtonActionPerformed(ActionEvent e) {
		fileChooser.setVisible(true);
		ficherosModel.addElement(fileChooser.getFile());
	}

}
