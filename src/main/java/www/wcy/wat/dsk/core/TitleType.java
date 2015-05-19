package www.wcy.wat.dsk.core;

public enum TitleType {
	WARNING("Ostrze¿enie"),
	INFO("Informacja"),
	ERROR("B³¹d");
	
	private String nazwa;
	
	private TitleType(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getNazwa() {
		return nazwa;
	}
}
