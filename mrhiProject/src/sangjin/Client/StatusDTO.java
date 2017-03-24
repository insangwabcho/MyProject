package sangjin.Client;

public class StatusDTO {
	private String status;

	/**
	 * 
	 */
	public StatusDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param status
	 */
	public StatusDTO(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StatusDTO [status=" + status + "]";
	}
	
	
}
