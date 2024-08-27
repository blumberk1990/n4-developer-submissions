package com.tridiumuniversity.checksumService;

import javax.baja.job.BJobService;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import java.util.zip.CRC32;
import java.util.zip.Checksum;



@NiagaraType
@NiagaraProperty(
        name = "checksum",
        type = "BLong",
        defaultValue = "BLong.make(0)",
        flags = Flags.SUMMARY
)
@NiagaraAction(
        name = "generateChecksum"
)
public class BChecksumService extends BAbstractService
{
//region /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
//@formatter:off
/*@ $com.tridiumuniversity.checksumService.BChecksumService(266381127)1.0$ @*/
/* Generated Tue Aug 27 12:15:01 CEST 2024 by Slot-o-Matic (c) Tridium, Inc. 2012-2024 */

  //region Property "checksum"

  /**
   * Slot for the {@code checksum} property.
   * @see #getChecksum
   * @see #setChecksum
   */
  public static final Property checksum = newProperty(Flags.SUMMARY, BLong.make(0).as(BLong.class).getLong(), null);

  /**
   * Get the {@code checksum} property.
   * @see #checksum
   */
  public long getChecksum() { return getLong(checksum); }

  /**
   * Set the {@code checksum} property.
   * @see #checksum
   */
  public void setChecksum(long v) { setLong(checksum, v, null); }

  //endregion Property "checksum"

  //region Action "generateChecksum"

  /**
   * Slot for the {@code generateChecksum} action.
   * @see #generateChecksum()
   */
  public static final Action generateChecksum = newAction(0, null);

  /**
   * Invoke the {@code generateChecksum} action.
   * @see #generateChecksum
   */
  public void generateChecksum() { invoke(generateChecksum, null, null); }

  //endregion Action "generateChecksum"

  //region Type

  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BChecksumService.class);

  //endregion Type

//@formatter:on
//endregion /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    @Override
    public Type[] getServiceTypes()
    {
        return new Type[] { BChecksumService.TYPE };
    }

    public void doGenerateChecksum(Context cx)
    {
        //BJobService.getService().submit(new BChecksumJob(), cx);
        if(getParentComponent().get("enabled").isValue()) {
            setChecksum(calculateCRC(Sys.getStation().getParentComponent()));
        }
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
}
