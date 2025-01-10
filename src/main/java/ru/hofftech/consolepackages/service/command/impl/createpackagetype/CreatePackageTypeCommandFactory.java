package ru.hofftech.consolepackages.service.command.impl.createpackagetype;

import lombok.RequiredArgsConstructor;
import ru.hofftech.consolepackages.datastorage.repository.PackageTypeRepository;
import ru.hofftech.consolepackages.service.command.Command;
import ru.hofftech.consolepackages.service.command.CommandAbstractFactory;
import ru.hofftech.consolepackages.service.command.CommandContext;
import ru.hofftech.consolepackages.service.command.CommandParser;

/**
 * The class implements the factory of commands for creating a package type.
 */
@RequiredArgsConstructor
public class CreatePackageTypeCommandFactory implements CommandAbstractFactory {

    private static final String NAME = "name";
    private static final String FORM = "form";
    private static final String DESCRIPTION = "description";

    private final PackageTypeRepository packageTypeRepository;

    /**
     * Creates the command to create a new package type.
     *
     * @param commandContext the context of the command
     * @return the command to create a new package type
     */
    @Override
    public Command createCommand(CommandContext commandContext) {
        return new CreatePackageTypeCommand((CreatePackageTypeContext) commandContext, packageTypeRepository);
    }


    /**
     * Creates the context of the command to create a new package type.
     *
     * @param strCommand the string with the command
     * @return the context of the command to create a new package type
     */
    @Override
    public CommandContext createCommandContext(String strCommand) {
        var commandKeyValues = CommandParser.parseCommandKeys(strCommand);

        var name = commandKeyValues.get(NAME);

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is empty");
        }

        var form = commandKeyValues.get(FORM);
        var description = commandKeyValues.get(DESCRIPTION);

        return new CreatePackageTypeContext(name, description, form);
    }
}
