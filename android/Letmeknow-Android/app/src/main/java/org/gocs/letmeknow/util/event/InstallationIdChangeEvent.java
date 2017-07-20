package org.gocs.letmeknow.util.event;

/**
 * Created by dynamicheart on 7/20/2017.
 */

public class InstallationIdChangeEvent {

    private String installationId;

    public InstallationIdChangeEvent(String installationId) {
        this.installationId = installationId;
    }

    public String getInstallationId() {
        return installationId;
    }

    public void setInstallationId(String installationId) {
        this.installationId = installationId;
    }
}
