package com.tridiumuniversity.checksumService;

import javax.baja.job.BSimpleJob;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.Context;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

@NiagaraType
public class BChecksumJob extends BSimpleJob
{
//region /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
//@formatter:off
/*@ $com.tridiumuniversity.checksumService.BChecksumJob(2979906276)1.0$ @*/
/* Generated Tue Aug 27 12:39:22 CEST 2024 by Slot-o-Matic (c) Tridium, Inc. 2012-2024 */

  //region Type

  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BChecksumJob.class);

  //endregion Type

//@formatter:on
//endregion /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    @Override
    public void run(Context cx)
    {
        //if(service == null) {
        //    throw new IllegalStateException("ChecksumService not available.");
        //}
        //BChecksumService service = (BChecksumService) Sys.getService(BChecksumService.TYPE);
        //if(getParentComponent().get("enabled").isValue()) {
        //    setChecksum(calculateCRC(Sys.getStation().getParentComponent()));
        //}
    }

    private long calculateCRC(BComponent component)
    {
        System.out.println("----------------------");
        System.out.println(component.toString());
        System.out.println("----------------------");
        String crcCalc = component.toString();
        byte[] bytes = crcCalc.getBytes();
        long retCalc = 0;
        long crc32Calculation = getCRC32Checksum(bytes);
        for(BComponent childComponent : component.getChildComponents())
        {
            retCalc = calculateCRC(childComponent);
        }
        return crc32Calculation + retCalc;
    }

    private static long getCRC32Checksum(byte[] bytes) {
        Checksum crc32 = new CRC32();
        crc32.update(bytes, 0, bytes.length);
        return crc32.getValue();
    }
    private boolean canceled = false;
}
