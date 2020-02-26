package de.uniba.dsg.dsam.model;

import java.io.Serializable;

public final class ValidationResult implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ResultType {
        OK, NOTOK
    }

    private ResultType type = null;
    private String errMsg = null;

    /**
     * @return the type
     */
    public ResultType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ResultType type) {
        this.type = type;
    }

    /**
     * @return the errMsg
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * @param errMsg the errMsg to set
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
