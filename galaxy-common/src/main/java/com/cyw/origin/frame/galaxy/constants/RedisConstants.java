package com.cyw.origin.frame.galaxy.constants;

public class RedisConstants {

    private static final String OPS_PACKAGEID_HKEY = "OPS_PACKAGEID_HKEY_%S";

    private static final String OPS_PID_WID_STOREID_HKEY = "OPS_PID_%S_WID_%s_STOREID_%s_HKEY";

    private static final String OPS_PACKAGEID_AUTH_KEY = "OPS_PACKAGEID_AUTH_KEY_%s";

    private static final String OPS_PACKAGEID_HKEY_DUBBO = "OPS_PACKAGEID_HKEY_DUBBO_%s";

    private static final String OPS_NOTICE_SOLUTION_ID = "OPS_NOTICE_SOLUTION_BY_ID_%s";

    private static final Long DEFULT_VALUE = -999999L;

    public static String getOpsPackageIdHkey(Long packageId) {
        Long tmpPackageId = packageId;
        if (tmpPackageId == null) {
            tmpPackageId = 0L;
        }

        return String.format(OPS_PACKAGEID_HKEY, tmpPackageId);
    }

    public static String getOpsPackageAuthKey(Long packageId) {
        Long tmpPackageId = packageId;
        if (tmpPackageId == null) {
            tmpPackageId = 0L;
        }

        return String.format(OPS_PACKAGEID_AUTH_KEY, tmpPackageId);
    }


    public static String getOpsPackageIdDubboKey(Long packageId) {
        Long tmpPackageId = packageId;
        if (tmpPackageId == null) {
            tmpPackageId = 0L;
        }

        return String.format(OPS_PACKAGEID_HKEY_DUBBO, tmpPackageId);
    }


    public static String getOpsWidStoreidHkey(Long pid, Long wid, Long storeId) {
        Long tmpPid = pid;
        Long tmpWid = wid;
        Long tmpStoreId = storeId;
        if (tmpPid == null) {
            tmpPid = DEFULT_VALUE;
        }
        if (tmpStoreId == null) {
            tmpStoreId = DEFULT_VALUE;
        }
        if (tmpWid == null) {
            tmpWid = DEFULT_VALUE;
        }

        return String.format(OPS_PID_WID_STOREID_HKEY, tmpPid, tmpWid, tmpStoreId);
    }

    public static String getOpsWidStoreidHkey() {
        return getOpsWidStoreidHkey(null, null, null);
    }

    public static String getNotcieSolutionkey(Long solutionId) {
        Long tmpId = solutionId;
        if (tmpId == null) {
            tmpId = 0L;
        }
        return String.format(OPS_NOTICE_SOLUTION_ID, tmpId);
    }
}
