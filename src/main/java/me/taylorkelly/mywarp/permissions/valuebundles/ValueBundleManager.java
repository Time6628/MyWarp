package me.taylorkelly.mywarp.permissions.valuebundles;

import java.util.Set;

import me.taylorkelly.mywarp.permissions.PermissionsManager;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import com.google.common.collect.ImmutableSortedSet;

/**
 * A ValueBundlemanager manages instances of value-bundles that all represent
 * the same value-type within the program logic. It acts as a storage and
 * provides additional methods to get the bundle needed.
 * 
 * @param <T>
 *            the value-bundle implementation this manager should manage
 */
public abstract class ValueBundleManager<T extends ValueBundle> {
    protected T defaultBundle;
    protected Set<T> bundles;

    /**
     * Initializes this value-bundle manager with the given bundles.
     * 
     * @param manager
     *            the permissions-manager that stores this value-bundle-manager
     * @param bundles
     *            all non-default bundles that a player can optionally have
     * @param defaultBundle
     *            the default bundle that acts as a fallback whenever none of
     *            the more specific bundles applies
     */
    public ValueBundleManager(PermissionsManager manager, Iterable<T> bundles, T defaultBundle) {
        this.bundles = ImmutableSortedSet.copyOf(bundles);
        this.defaultBundle = defaultBundle;

        registerPermissions(manager);
    }

    /**
     * Registers permissions from the stored bundles. By default, this method
     * registers the full-permission from each configured bundle. It can be
     * expanded to register additional permissions, if needed.
     * 
     * This method should <b>never</b> be called manually, since it is
     * automatically called upon creation of the containing instance.
     * 
     * @param manager
     *            the permissions-manager that stores this value-bundle-manager
     */
    protected void registerPermissions(PermissionsManager manager) {
        for (ValueBundle bundle : bundles) {
            manager.registerPermission(new Permission(bundle.getPermission(), PermissionDefault.FALSE));
        }
    }
}