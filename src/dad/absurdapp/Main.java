package dad.absurdapp;

import java.io.IOException;

import javax.swing.UIManager;

public class Main {

	public static final String LAF_METAL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public static void setLookAndFeel(String laf){
		try{
			UIManager.setLookAndFeel(LAF_METAL);
		}catch (Exception e){
			System.out.println("error");
		}
	}
	
	public static void main(String[] args) throws IOException {
		//setLookAndFeel(LAF_METAL);
		new AbsudaFrame().setVisible(true);
		
	}

}
