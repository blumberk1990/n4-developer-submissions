package com.tridiumuniversity.driverFinder;

import javax.baja.collection.BITable;
import javax.baja.collection.Column;
import javax.baja.collection.ColumnList;
import javax.baja.collection.TableCursor;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;


@NiagaraType
@NiagaraProperty(
        name = "searchOrd",
        type = "BOrd",
        defaultValue = "BOrd.make(\"file:^traffic/trafficStats.properties\")",
        facets = {
                @Facet(name = "BFacets.TARGET_TYPE", value = "\"baja:IFile\"")
        }
)
@NiagaraAction(
        name = "find"
)
public class BDeviceFinder extends BComponent {
//region /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
//@formatter:off
/*@ $com.tridiumuniversity.driverFinder.BDeviceFinder(3552482551)1.0$ @*/
/* Generated Tue Aug 27 09:30:51 CEST 2024 by Slot-o-Matic (c) Tridium, Inc. 2012-2024 */

  //region Property "searchOrd"

  /**
   * Slot for the {@code searchOrd} property.
   * @see #getSearchOrd
   * @see #setSearchOrd
   */
  public static final Property searchOrd = newProperty(0, BOrd.make("file:^traffic/trafficStats.properties"), BFacets.make(BFacets.TARGET_TYPE, "baja:IFile"));

  /**
   * Get the {@code searchOrd} property.
   * @see #searchOrd
   */
  public BOrd getSearchOrd() { return (BOrd)get(searchOrd); }

  /**
   * Set the {@code searchOrd} property.
   * @see #searchOrd
   */
  public void setSearchOrd(BOrd v) { set(searchOrd, v, null); }

  //endregion Property "searchOrd"

  //region Action "find"

  /**
   * Slot for the {@code find} action.
   * @see #find()
   */
  public static final Action find = newAction(0, null);

  /**
   * Invoke the {@code find} action.
   * @see #find
   */
  public void find() { invoke(find, null, null); }

  //endregion Action "find"

  //region Type

  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BDeviceFinder.class);

  //endregion Type

//@formatter:on
//endregion /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public void doFind(Context cx)
    {
      //Pattern regex = Pattern.compile(getPointNameRegex());
      BITable<? extends BIObject> table = (BITable<? extends BIObject>) BOrd.make(
              "bql:select * from driver:DeviceNetwork where toString != 'Niagara Network'"
      ).get(Sys.getStation(), cx);
      ColumnList columns = table.getColumns();
      Column slotPathColumn = columns.get("slotPath");

      double total = 0.0;
      try (TableCursor<? extends BIObject> cursor = table.cursor())
      {
        while (cursor.next())
        {
          String name = ((BString) cursor.cell(slotPathColumn)).getString();
          //System.out.println("name: " + name);
          logger.info(String.format("Driver component slotPath: %s.", name));
        }
      }
      //local:|foxs:|station:|slot:/|bql:select * from driver:DeviceNetwork where toString != 'Niagara Network'
      //local:|foxs:|station:|slot:/|bql:select * from driver:DeviceNetwork where slotPath not like 'slot:/Drivers%'
    }
  private static final Logger logger = Logger.getLogger("driverFinder");
}
