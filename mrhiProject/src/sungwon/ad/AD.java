package sungwon.ad;

import java.util.ArrayList;

import javax.swing.JLabel;

public class AD extends Thread {
	private ArrayList<String> ad_path;
	private String ad_img;
	private String aa;
	private JLabel lab;
	 
	public String getAd_img() {
		return ad_img;
	}
	public void setAd_img(String ad_img) {
		this.ad_img = ad_img;
	}
	
	public AD(JLabel lab){
		ad_path= new ArrayList<>();
		ad_path.add("AD/AD0.PNG");
		ad_path.add("AD/AD1.PNG");
		ad_path.add("AD/AD2.PNG");
		ad_path.add("AD/AD3.PNG");
		ad_path.add("AD/AD4.PNG");
		ad_path.add("AD/AD5.PNG");
		this.lab=lab;
		Thread th = new Thread(this);
		th.start();
	}
	@Override
		public void run() {
			try {
				while (true) {
					for (int i = 0; i < ad_path.size(); i++) {
						Thread.sleep(3000);
						setAd_img(ad_path.get(i)); 
						aa= getAd_img();
						lab.setText(getAd_img());
						if (i > ad_path.size()) {
							i = 0;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
