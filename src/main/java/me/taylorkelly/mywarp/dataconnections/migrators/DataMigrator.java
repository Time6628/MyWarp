package me.taylorkelly.mywarp.dataconnections.migrators;

import java.util.Collection;

import com.google.common.util.concurrent.ListenableFuture;

import me.taylorkelly.mywarp.data.Warp;

/**
 * A data migrator loads Warps from a read-only data source.
 */
public interface DataMigrator {

    /**
     * Gets all warps from the underlying data-source and cleans up afterwards.
     * 
     * @return a ListanbleFuture containing a COllection of all loaded warps.
     */
    public ListenableFuture<Collection<Warp>> getWarps();

}