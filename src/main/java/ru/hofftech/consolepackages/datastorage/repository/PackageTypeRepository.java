package ru.hofftech.consolepackages.datastorage.repository;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hofftech.consolepackages.datastorage.model.entity.PackageType;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

/**
 * Interface for package type repository
 */
@Repository
public interface PackageTypeRepository extends JpaRepository<PackageType, Long> {

    /**
     * Finds package types by a collection of IDs.
     *
     * @param ids a collection of package type IDs
     * @return a list of package types that match the given IDs
     */
    List<PackageType> findPackageTypesByIdIsIn(Collection<Long> ids);
}
