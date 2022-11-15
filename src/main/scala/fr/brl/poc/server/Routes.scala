package fr.brl.poc
package server

import cats.effect.IO
import sttp.tapir.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import sttp.tapir.server.http4s.serverSentEventsBody

import java.util.UUID
import sttp.capabilities.fs2.Fs2Streams
import sttp.model.sse.ServerSentEvent

object Routes {

  private val SseApiBase = infallibleEndpoint.in("sse")

  val GetSSEData: Endpoint[Unit, Unit, Unit, fs2.Stream[cats.effect.IO, ServerSentEvent], Fs2Streams[cats.effect.IO]] = endpoint.get
    .out(serverSentEventsBody[IO])
}
