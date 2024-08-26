package com.tridiumuniversity.recursiveUnhider;

import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

@NiagaraType
@NiagaraProperty(
        name = "regex",
        type = "BString",
        defaultValue = "*",
        flags = Flags.SUMMARY
)
@NiagaraAction(
        name = "unhide"
)
public class BUnhideSlots extends BComponent
{
//region /*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
//@formatter:off
/*@ $com.tridiumuniversity.recursiveUnhider.BUnhideSlots(1750277789)1.0$ @*/
/* Generated Mon Aug 26 16:48:30 CEST 2024 by Slot-o-Matic (c) Tridium, Inc. 2012-2024 */

  //region Property "regex"

  /**
   * Slot for the {@code regex} property.
   * @see #getRegex
   * @see #setRegex
   */
  public static final Property regex = newProperty(Flags.SUMMARY, "*", null);

  /**
   * Get the {@code regex} property.
   * @see #regex
   */
  public String getRegex() { return getString(regex); }

  /**
   * Set the {@code regex} property.
   * @see #regex
   */
  public void setRegex(String v) { setString(regex, v, null); }

  //endregion Property "regex"

  //region Action "unhide"

  /**
   * Slot for the {@code unhide} action.
   * @see #unhide()
   */
  public static final Action unhide = newAction(0, null);

  /**
   * Invoke the {@code unhide} action.
   * @see #unhide
   */
  public void unhide() { invoke(unhide, null, null); }

  //endregion Action "unhide"

  //region Type

  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BUnhideSlots.class);

  //endregion Type

//@formatter:on
//endregion /*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public void doUnhide(Context cx)
    {
        recSlotUnnchecker(getParentComponent(), cx);
    }

    public void recSlotUnnchecker(BComponent component, Context cx)
    {
        for (Slot slot : component.getSlots())
        {
            if(getRegex().equals(slot.getName()) || getRegex().equals("*"))
            {
                Flags.remove(component, slot, cx, Flags.HIDDEN);
            }
        }
        for(BComponent child : component.getChildComponents())
        {
            recSlotUnnchecker(child, cx);
        }
    }
}
