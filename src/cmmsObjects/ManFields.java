package cmmsObjects;

public class ManFields {
	private boolean id;
	private boolean type;
	private boolean manufacturer;
	private boolean model;
	private boolean kmsDriven;
	private boolean kmsLastServiced;
	private boolean dateLastServiced;
	private boolean insInfo;
	private boolean year;
	
	public ManFields(){
		this.id = true;
		this.type = true;
		this.manufacturer = true;
		this.model = true;
		this.kmsDriven = true;
		this.kmsLastServiced = true;
		this.dateLastServiced = true;
		this.insInfo = true;
		this.year = true;
	}
	
	public ManFields(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f, boolean g, boolean h, boolean i) {
	    this.id = a;
        this.type = b;
        this.manufacturer = c;
        this.model = d;
        this.kmsDriven = e;
        this.kmsLastServiced = f;
        this.dateLastServiced = g;
        this.insInfo = h;
        this.year = i;
	}
	
	public boolean getId() {
		return id;
	}
	public boolean setId(boolean id) {
		this.id = id;
		return this.id;
	}
	public boolean getType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	public boolean getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(boolean manufacturer) {
		this.manufacturer = manufacturer;
	}
	public boolean getModel() {
		return model;
	}
	public void setModel(boolean model) {
		this.model = model;
	}
	public boolean getKmsDriven() {
		return kmsDriven;
	}
	public void setKmsDriven(boolean kmsDriven) {
		this.kmsDriven = kmsDriven;
	}
	public boolean getKmsLastServiced() {
		return kmsLastServiced;
	}
	public void setKmsLastServiced(boolean kmsLastServiced) {
		this.kmsLastServiced = kmsLastServiced;
	}
	public boolean getInsInfo() {
		return insInfo;
	}
	public void setInsInfo(boolean insInfo) {
		this.insInfo = insInfo;
	}
	public boolean getYear() {
		return year;
	}
	public void setYear(boolean year) {
		this.year = year;
	}

    public boolean getDateLastServiced() {
        return dateLastServiced;
    }

    public void setDateLastServiced(boolean dateLastServiced) {
        this.dateLastServiced = dateLastServiced;
    }
	
	
}
