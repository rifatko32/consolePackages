package ru.hofftech.consolepackages.service.command.impl.createpackagetype;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.hofftech.consolepackages.model.entity.PackageType;
import ru.hofftech.consolepackages.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;

/**
 * Command to create new package type.
 */
@Slf4j
@RequiredArgsConstructor
public class CreatePackageTypeCommand implements Command {

    private final CreatePackageTypeContext context;
    private final PackageTypeRepository packageTypeRepository;

    /**
     * Executes the command.
     */
    @Override
    public void execute() {
        packageTypeRepository.save(
                new PackageType.Builder()
                        .id(context.id())
                        .form(context.form())
                        .descriptionNumber(context.description())
                        .build());
    }
}
