package edu.udacity.mou.meeckets.data.datasources.network.tournaments;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Singleton;

import edu.udacity.mou.meeckets.data.NetworkUtils;
import edu.udacity.mou.meeckets.domain.exceptions.server.InternalServerErrorException;
import edu.udacity.mou.meeckets.domain.exceptions.server.ServerException;
import edu.udacity.mou.meeckets.domain.model.tournaments.Location;
import edu.udacity.mou.meeckets.domain.model.tournaments.Tournament;

/**
 * Created by mou on 11/13/17.
 */

@Singleton
public class MockTournamentsNetworkDatasource implements ITournamentsNetworkDatasource {
    private static final int LOGIN_FAIL_RATE = 5;

    @Override
    public List<Tournament> tournaments() throws ServerException {
        if (!NetworkUtils.fakeRequest(LOGIN_FAIL_RATE)) {
            throw new InternalServerErrorException();
        }

        return tournamentsMocked;
    }

    private String descriptionMocked = "Play this amazing tournament and win a lot of awards. But, most important thing, have a lot of fun playing with others who have same hobbies!";

    private List<Location> locationsMocked = Arrays.asList(
            Location.builder()
                    .name("Alita Comics")
                    .latitude(43.3624382f)
                    .longitude(-8.4164868f)
                    .build(),

            Location.builder()
                    .name("Dr Panush")
                    .latitude(43.3558156f)
                    .longitude(-8.4042196f)
                    .build(),

            Location.builder()
                    .name("Zacatrus")
                    .latitude(40.4351747f)
                    .longitude(-3.7128877f)
                    .build(),

            Location.builder()
                    .name("Asociación Cultural Jugamos Tod@s")
                    .latitude(37.8921356f)
                    .longitude(-4.7872465f)
                    .build(),

            Location.builder()
                    .name("Crazy Squirrel")
                    .latitude(36.8237493f)
                    .longitude(-119.7799368f)
                    .build()
    );

    private List<Tournament> tournamentsMocked = Arrays.asList(
            Tournament.builder()
                    .id(1)
                    .image("file:///android_asset/tournaments/7thcontinent.jpg")
                    .location(locationsMocked.get(0))
                    .date(new Date(1514991600000L))
                    .description(descriptionMocked)
                    .name("7th Continent")
                    .build(),
            Tournament.builder()
                    .id(2)
                    .image("file:///android_asset/tournaments/bloodbowl.jpg")
                    .location(locationsMocked.get(1))
                    .date(new Date(1515776400000L))
                    .description(descriptionMocked)
                    .name("Blood Bowl")
                    .build(),
            Tournament.builder()
                    .id(3)
                    .image("file:///android_asset/tournaments/clansofcaledonia.png")
                    .location(locationsMocked.get(2))
                    .date(new Date(1513789200000L))
                    .description(descriptionMocked)
                    .name("Clans of Caledonia")
                    .build(),
            Tournament.builder()
                    .id(4)
                    .image("file:///android_asset/tournaments/cottagegarden.jpg")
                    .location(locationsMocked.get(3))
                    .date(new Date(1514566800000L))
                    .description(descriptionMocked)
                    .name("Cottage Garden")
                    .build(),
            Tournament.builder()
                    .id(5)
                    .image("file:///android_asset/tournaments/diceforge.jpg")
                    .location(locationsMocked.get(4))
                    .date(new Date(1514584800000L))
                    .description(descriptionMocked)
                    .name("Dice Forge")
                    .build(),
            Tournament.builder()
                    .id(6)
                    .image("file:///android_asset/tournaments/dixit.jpg")
                    .location(locationsMocked.get(0))
                    .date(new Date(1517493600000L))
                    .description(descriptionMocked)
                    .name("Dixit")
                    .build(),
            Tournament.builder()
                    .id(7)
                    .image("file:///android_asset/tournaments/evolution.jpg")
                    .location(locationsMocked.get(1))
                    .date(new Date(1516024800000L))
                    .description(descriptionMocked)
                    .name("Evolution")
                    .build(),
            Tournament.builder()
                    .id(8)
                    .image("file:///android_asset/tournaments/formulad.jpg")
                    .location(locationsMocked.get(2))
                    .date(new Date(1516532400000L))
                    .description(descriptionMocked)
                    .name("Formula D")
                    .build(),
            Tournament.builder()
                    .id(9)
                    .image("file:///android_asset/tournaments/isleofskye.jpg")
                    .location(locationsMocked.get(3))
                    .date(new Date(1517331600000L))
                    .description(descriptionMocked)
                    .name("Isle of Skye")
                    .build(),
            Tournament.builder()
                    .id(10)
                    .image("file:///android_asset/tournaments/kodama.jpg")
                    .location(locationsMocked.get(4))
                    .date(new Date(1515186000000L))
                    .description(descriptionMocked)
                    .name("Kodama")
                    .build(),
            Tournament.builder()
                    .id(11)
                    .image("file:///android_asset/tournaments/photosynthesis.jpg")
                    .location(locationsMocked.get(0))
                    .date(new Date(1515178800000L))
                    .description(descriptionMocked)
                    .name("Photosynthesis")
                    .build(),
            Tournament.builder()
                    .id(12)
                    .image("file:///android_asset/tournaments/potionexplotion.jpg")
                    .location(locationsMocked.get(1))
                    .date(new Date(1515783600000L))
                    .description(descriptionMocked)
                    .name("Potion Explotion")
                    .build(),
            Tournament.builder()
                    .id(13)
                    .image("file:///android_asset/tournaments/seasons.jpg")
                    .location(locationsMocked.get(2))
                    .date(new Date(1518444000000L))
                    .description(descriptionMocked)
                    .name("Seasons")
                    .build(),
            Tournament.builder()
                    .id(14)
                    .image("file:///android_asset/tournaments/smallworld.jpg")
                    .location(locationsMocked.get(3))
                    .date(new Date(1515765600000L))
                    .description(descriptionMocked)
                    .name("SmallWorld")
                    .build(),
            Tournament.builder()
                    .id(15)
                    .image("file:///android_asset/tournaments/terramystica.jpg")
                    .location(locationsMocked.get(4))
                    .date(new Date(1515880800000L))
                    .description(descriptionMocked)
                    .name("Terra Mystica")
                    .build(),
            Tournament.builder()
                    .id(16)
                    .image("file:///android_asset/tournaments/tinyepicquest.jpg")
                    .location(locationsMocked.get(0))
                    .date(new Date(1514314800000L))
                    .description(descriptionMocked)
                    .name("Tiny Epic Quest")
                    .build(),
            Tournament.builder()
                    .id(17)
                    .image("file:///android_asset/tournaments/tokaido.jpg")
                    .location(locationsMocked.get(1))
                    .date(new Date(1514660400000L))
                    .description(descriptionMocked)
                    .name("Tokaido")
                    .build(),
            Tournament.builder()
                    .id(18)
                    .image("file:///android_asset/tournaments/tzolkin.jpg")
                    .location(locationsMocked.get(2))
                    .date(new Date(1513868400000L))
                    .description(descriptionMocked)
                    .name("Tzolk'in")
                    .build(),
            Tournament.builder()
                    .id(19)
                    .image("file:///android_asset/tournaments/zombicide.jpg")
                    .location(locationsMocked.get(3))
                    .date(new Date(1514646000000L))
                    .description(descriptionMocked)
                    .name("Zombicide")
                    .build()
    );
}
