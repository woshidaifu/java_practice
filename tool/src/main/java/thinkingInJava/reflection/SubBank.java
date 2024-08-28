package thinkingInJava.reflection;

import lombok.Data;

/**
 * 假设SubBank类是我行分行类，包含私有的分行名称、地址、行长信息等
 * 使用lombok注解，省略set get方法
 */
@Data
public class SubBank {
    private String bankName;
    private String bankAddress;
    private String bankBoss;

    //构造方法
    public SubBank(String bankName, String bankAddress, String bankBoss) {
        this.bankName = bankName;
        this.bankAddress = bankAddress;
        this.bankBoss = bankBoss;
    }

    public SubBank() {
    }
}
