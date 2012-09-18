package playground;

import nl.loxia.railcloud.MetaInfo;
import nl.loxia.railcloud.Value;
import nl.loxia.railcloud.util.ValueAdapter;

public class Sand implements Value
{
    private ValueAdapter delegate = ValueAdapter.delegateFor( Sand.class );

    public String getColor()
    {
        return delegate.get("color");
    }

    public void setColor(String color)
    {
        delegate.set("color", color);
    }

    @Override
    public MetaInfo getMetaInfo()
    {
        return delegate.getMetaInfo();
    }

    @Override
    public Class<Sand> getClassType()
    {
        return Sand.class;
    }

    @Override
    public String toJSON()
    {
        return delegate.toJSON();
    }
}
