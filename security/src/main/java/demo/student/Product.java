package demo.student;


public class Product {
    private   Integer productId;
    private   String productName;

    public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Product(Integer studentId,
                   String studentName) {
        this.productId = studentId;
        this.productName = studentName;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }
}
