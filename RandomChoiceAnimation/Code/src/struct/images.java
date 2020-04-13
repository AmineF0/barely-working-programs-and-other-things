package struct;

import javax.swing.ImageIcon;

public enum images {

	btn("btn.jpg"),
	btn2("btn2.jpg"),
	chosen("chosen.jpg");
	
	private String url;
	private ImageIcon img ;
			
	images(String url){
		setUrl(url);
		setImg(new ImageIcon(url));
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon imageIcon) {
		this.img = imageIcon;
	}
	
}
