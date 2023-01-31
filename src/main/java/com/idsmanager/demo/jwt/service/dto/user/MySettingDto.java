
package com.idsmanager.demo.jwt.service.dto.user;

import java.io.Serializable;

/**
 * 2015/12/21
 *
 * @author Shengzhao Li
 */
public class MySettingDto implements Serializable {
    private static final long serialVersionUID = -3572373435351085365L;


    private String oldPassword;

    private String newPassword;

    private String reNewPassword;


    public MySettingDto() {
    }


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
    }
}
