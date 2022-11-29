package fr.brl.poc
package server

import cats.effect.*
import cats.syntax.all.*
import io.circe.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import fs2.*
import fs2.concurrent.*
import sttp.model.sse.ServerSentEvent

import java.time.Instant
import scala.concurrent.duration.*
import scala.util.Random

class LightningService[F[_]: Temporal](using F: Sync[F]) {

  def startPublisher: Stream[F, ServerSentEvent] =
    Stream
      .iterate(1)(_ + 1).covary[F]
      .evalMap(i =>
        F.delay(
          ServerSentEvent(
            data = Some(Lightning(
              lat = Random.between(-90.0, 90.0),
              lng = Random.between(-180.0, 180.0),
              Instant.now().minusSeconds(Random.between(-1_000, 0))
            ).asJson.noSpaces),
            eventType = Some("Lightning"),
            id = Some(i.toString),
            retry = None
          )
        )
      ).metered(500.millis)

}
