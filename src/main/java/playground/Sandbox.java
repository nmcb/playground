package playground;

import nl.loxia.railcloud.playground.ISandbox;
import nl.loxia.railcloud.util.ValueAdapter;

/**
 * @author Marco Borst
 * @since 16/07/12
 */
public class Sandbox extends ValueAdapter implements ISandbox
{
    @Override
    public String getName()
    {
        return get("name");
    }

    @Override
    public void setName(String name)
    {
        set("name", name);
    }

    @Override
    public Class<Sandbox> getClassType()
    {
        return Sandbox.class;
    }
}
