package com.meidusa.venus.util;

import java.security.SecureRandom;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("rawtypes")
public class VenusLoggerUtil {
    private static SecureRandom numberGenerator = new SecureRandom();
    public static final String REQUEST_TRACE_ID = "REQUEST_TRACE_ID";
    private static final String REQUEST_TRACE_MSG_WITH_PARAMS = "request id={},service={},params={}";
    private static final String REQUEST_TRACE_MSG_WITHOUT_PARAMS = "request id={},service={}";

    private static final String RECEIVE_TRACE_MSG_WITH_PARAMS = "receive id={},service={},params={}";
    private static final String RECEIVE_TRACE_MSG_WITHOUT_PARAMS = "receive id={},service={}";

    private static final String CALLBACK_TRACE_MSG_WITH_PARAMS = "callback id={},service={},params={}";
    private static final String CALLBACK_TRACE_MSG_WITHOUT_PARAMS = "callback id={},service={}";

    private static Logger REUEST_LOGGER = LoggerFactory.getLogger("venus.tracer");

    /**
     * from java.net.UUID
     * 
     * Static factory to retrieve a type 4 (pseudo randomly generated) UUID.
     * 
     * The <code>UUID</code> is generated using a cryptographically strong pseudo random number generator.
     * 
     * 
     * @return a randomly generated <tt>UUID</tt>.
     */
    public static byte[] randomUUID() {
        SecureRandom ng = numberGenerator;

        byte[] randomBytes = new byte[16];
        ng.nextBytes(randomBytes);
        randomBytes[6] &= 0x0f; /* clear version */
        randomBytes[6] |= 0x40; /* set to version 4 */
        randomBytes[8] &= 0x3f; /* clear variant */
        randomBytes[8] |= 0x80; /* set to IETF variant */
        return randomBytes;
    }

    /**
     * 閸欐垼鎹ｇ拠閿嬬湴閻ㄥ嫭妫╄箛锟�
     * 
     * @param traceId
     * @param apiName
     * @param params
     */
    public static void logRequest(byte[] traceId, String apiName, Map params) {
        if (REUEST_LOGGER.isDebugEnabled()) {
            REUEST_LOGGER.debug(REQUEST_TRACE_MSG_WITH_PARAMS, new Object[] { new UUID(traceId).toString(), apiName, params });
        } else if (REUEST_LOGGER.isInfoEnabled()) {
            REUEST_LOGGER.info(REQUEST_TRACE_MSG_WITHOUT_PARAMS, new UUID(traceId).toString(), apiName);
        }
    }

    /**
     * 閸欐垼鎹ｇ拠閿嬬湴閻ㄥ嫭妫╄箛锟�
     * 
     * @param traceId
     * @param apiName
     */
    public static void logRequest(byte[] traceId, String apiName) {
        if (REUEST_LOGGER.isDebugEnabled() || REUEST_LOGGER.isInfoEnabled()) {
            REUEST_LOGGER.info(REQUEST_TRACE_MSG_WITHOUT_PARAMS, new UUID(traceId).toString(), apiName);
        }
    }

    /**
     * 閹恒儱褰堢拠閿嬬湴閻ㄥ嫭妫╄箛锟�
     * 
     * @param traceId
     * @param apiName
     * @param params
     */
    public static void logReceive(byte[] traceId, String apiName, Map params) {
        if (REUEST_LOGGER.isDebugEnabled()) {
            REUEST_LOGGER.debug(RECEIVE_TRACE_MSG_WITH_PARAMS, new Object[] { new UUID(traceId).toString(), apiName, Utils.toString(params) });
        } else if (REUEST_LOGGER.isInfoEnabled()) {
            REUEST_LOGGER.info(RECEIVE_TRACE_MSG_WITHOUT_PARAMS, new UUID(traceId).toString(), apiName);
        }
    }

    /**
     * 閹恒儱褰堢拠閿嬬湴閻ㄥ嫭妫╄箛锟�
     * 
     * @param traceId
     * @param apiName
     */
    public static void logReceive(byte[] traceId, String apiName) {
        if (REUEST_LOGGER.isDebugEnabled() || REUEST_LOGGER.isInfoEnabled()) {
            REUEST_LOGGER.info(RECEIVE_TRACE_MSG_WITHOUT_PARAMS, new UUID(traceId).toString(), apiName);
        }
    }

    /**
     * 閸ョ偠鐨熼惃鍕）韫囷拷
     * 
     * @param traceId
     * @param apiName
     * @param params
     */
    public static void logCallback(byte[] traceId, String apiName, Map params) {
        if (REUEST_LOGGER.isDebugEnabled()) {
            REUEST_LOGGER.debug(CALLBACK_TRACE_MSG_WITH_PARAMS, new Object[] { new UUID(traceId).toString(), apiName, Utils.toString(params) });
        } else if (REUEST_LOGGER.isInfoEnabled()) {
            REUEST_LOGGER.info(CALLBACK_TRACE_MSG_WITHOUT_PARAMS, new UUID(traceId).toString(), apiName);
        }
    }

    /**
     * 閸ョ偠鐨熼惃鍕）韫囷拷
     * 
     * @param traceId
     * @param apiName
     */
    public static void logCallback(byte[] traceId, String apiName) {
        if (REUEST_LOGGER.isDebugEnabled() || REUEST_LOGGER.isInfoEnabled()) {
            REUEST_LOGGER.info(CALLBACK_TRACE_MSG_WITHOUT_PARAMS, new UUID(traceId).toString(), apiName);
        }
    }

    public static class UUID {

        /*
         * The most significant 64 bits of this UUID.
         * @serial
         */
        private final long mostSigBits;

        /*
         * The least significant 64 bits of this UUID.
         * @serial
         */
        private final long leastSigBits;

        /*
         * Private constructor which uses a byte array to construct the new UUID.
         */
        private UUID(byte[] data) {
            long msb = 0;
            long lsb = 0;
            assert data.length == 16;
            for (int i = 0; i < 8; i++)
                msb = (msb << 8) | (data[i] & 0xff);
            for (int i = 8; i < 16; i++)
                lsb = (lsb << 8) | (data[i] & 0xff);
            this.mostSigBits = msb;
            this.leastSigBits = lsb;
        }

        public String toString() {
            return (digits(mostSigBits >> 32, 8) + "-" + digits(mostSigBits >> 16, 4) + "-" + digits(mostSigBits, 4) + "-" + digits(leastSigBits >> 48, 4)
                    + "-" + digits(leastSigBits, 12));
        }

        /** Returns val represented by the specified number of hex digits. */
        private static String digits(long val, int digits) {
            long hi = 1L << (digits * 4);
            return Long.toHexString(hi | (val & (hi - 1))).substring(1);
        }
    }
}
