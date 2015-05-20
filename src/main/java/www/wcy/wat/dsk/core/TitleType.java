package www.wcy.wat.dsk.core;

public enum TitleType {
	WARNING("Warning"),
	INFO("Information"),
	ERROR("Error");
	
	private String nazwa;
	
	private TitleType(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getNazwa() {
		return nazwa;
	}
}
