package agency.akcom.upwork.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

public class Dto implements IsSerializable, Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

