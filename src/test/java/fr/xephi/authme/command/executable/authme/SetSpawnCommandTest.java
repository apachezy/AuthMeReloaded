package fr.xephi.authme.command.executable.authme;

import fr.xephi.authme.settings.SpawnLoader;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;

/**
 * Test for {@link SetSpawnCommand}.
 */
@RunWith(MockitoJUnitRunner.class)
public class SetSpawnCommandTest {

    @InjectMocks
    private SetSpawnCommand command;

    @Mock
    private SpawnLoader spawnLoader;


    @Test
    public void shouldSetSpawn() {
        // given
        Player player = mock(Player.class);
        Location location = mock(Location.class);
        given(player.getLocation()).willReturn(location);
        given(spawnLoader.setSpawn(location)).willReturn(true);

        // when
        command.executeCommand(player, Collections.emptyList());

        // then
        verify(spawnLoader).setSpawn(location);
        verify(player).sendMessage(argThat(containsString("defined new spawn")));
    }

    @Test
    public void shouldHandleError() {
        // given
        Player player = mock(Player.class);
        Location location = mock(Location.class);
        given(player.getLocation()).willReturn(location);
        given(spawnLoader.setSpawn(location)).willReturn(false);

        // when
        command.executeCommand(player, Collections.emptyList());

        // then
        verify(spawnLoader).setSpawn(location);
        verify(player).sendMessage(argThat(containsString("has failed")));
    }
}
