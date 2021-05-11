package app;

public class Catalog {
    private   Integer catalogId;
    private   String catalogName;

    public Catalog(Integer catalogId, String catalogName) {
		super();
		this.catalogId = catalogId;
		this.catalogName = catalogName;
	}


	public Integer getCatalogId() {
		return catalogId;
	}


	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}


	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

     
}
