package entry;

public class MybatisGenEntry {
    public static void main(String[] args) {
        String configFile = "../mybatis-gen/gen_galaxy.xml";
        args = new String[]{"-configfile", MybatisGenEntry.class.getResource(configFile).getFile(), "-overwrite"};
        org.mybatis.generator.api.ShellRunner.main(args);
    }
}
