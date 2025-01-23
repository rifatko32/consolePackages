package ru.hofftech.consolepackages.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.hofftech.consolepackages.service.command.AbstractFactoryProvider;
import ru.hofftech.consolepackages.service.command.CommandContextWithResult;
import ru.hofftech.consolepackages.service.command.impl.createpackagetype.CreatePackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.deletepackagetype.DeletePackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.editpackagetype.EditPackageTypeCommandFactory;
import ru.hofftech.consolepackages.service.command.impl.findpackagetype.FindPackageTypeCommandFactory;

import static ru.hofftech.consolepackages.service.command.CommandConstants.CREATE_COMMAND_PATTERN;
import static ru.hofftech.consolepackages.service.command.CommandConstants.DELETE_COMMAND_PATTERN;
import static ru.hofftech.consolepackages.service.command.CommandConstants.EDIT_COMMAND_PATTERN;
import static ru.hofftech.consolepackages.service.command.CommandConstants.FIND_COMMAND_PATTERN;
import static ru.hofftech.consolepackages.shellconstants.CommandOptions.DESCRIPTION;
import static ru.hofftech.consolepackages.shellconstants.CommandOptions.FORM;
import static ru.hofftech.consolepackages.shellconstants.CommandOptions.NAME;

/**
 * This component provides methods for Spring Shell to execute commands.
 * <p>
 * Spring Shell will find all methods annotated with {@link ShellMethod} and
 * expose them as commands.
 * </p>
 */
@RequiredArgsConstructor
@ShellComponent
public class PackageTypeController {

    private final AbstractFactoryProvider abstractFactoryProvider;
    private final CreatePackageTypeCommandFactory createPackageTypeCommandFactory;

    /**
     * Returns a list of all package types.
     *
     * @return a list of all package types
     */
    @ShellMethod(key = FIND_COMMAND_PATTERN)
    public String findPackageTypes(
            @ShellOption(value = {NAME}) String name
    ) {
        var factory = abstractFactoryProvider.returnCommandAbstractFactory(FIND_COMMAND_PATTERN);

        var context = ((FindPackageTypeCommandFactory) factory).createCommandContextByParameters(name);
        var command = factory.createCommand(context);
        command.execute();

        return ((CommandContextWithResult<?>) context).getResult().toString();
    }

    /**
     * Returns a list of all package types.
     *
     * @param name the name of the package type
     * @return a list of all package types
     */
    @ShellMethod(key = CREATE_COMMAND_PATTERN)
    public String createPackageType(
            @ShellOption(value = {NAME}) String name,
            @ShellOption(value = {FORM}) String form,
            @ShellOption(value = {DESCRIPTION}) String description
    ) {
        var factory = abstractFactoryProvider.returnCommandAbstractFactory(CREATE_COMMAND_PATTERN);

        var context = ((CreatePackageTypeCommandFactory) factory).createCommandContextByParameters(name, form, description);
        var command = factory.createCommand(context);
        command.execute();

        return String.format("Package type %s created", name);
    }

    /**
     * Deletes a package type.
     *
     * @param name the name of the package type
     * @return a message indicating that the package type was deleted
     */
    @ShellMethod(key = DELETE_COMMAND_PATTERN)
    public String deletePackageType(
            @ShellOption(value = {NAME}) String name
    ) {
        var factory = abstractFactoryProvider.returnCommandAbstractFactory(DELETE_COMMAND_PATTERN);

        var context = ((DeletePackageTypeCommandFactory) factory).createCommandContextByParameters(name);
        var command = factory.createCommand(context);
        command.execute();

        return String.format("Package type %s deleted", name);
    }

    /**
     * Edits a package type.
     *
     * @param name        the name of the package type
     * @param form        the new form of the package type
     * @param description the new description of the package type
     * @return a message indicating that the package type was edited
     */
    @ShellMethod(key = EDIT_COMMAND_PATTERN)
    public String editPackageType(
            @ShellOption(value = {NAME}) String name,
            @ShellOption(value = {FORM}) String form,
            @ShellOption(value = {DESCRIPTION}) String description
    ) {
        var factory = abstractFactoryProvider.returnCommandAbstractFactory(EDIT_COMMAND_PATTERN);

        var context = ((EditPackageTypeCommandFactory) factory).createCommandContextByParameters(name, form, description);
        var command = factory.createCommand(context);
        command.execute();

        return String.format("Package type %s edited", name);
    }
}
