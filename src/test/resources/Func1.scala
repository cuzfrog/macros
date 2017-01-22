
(s: String) =>
  s match {
    case "1" => "one"
    case other => other + "(other)"
  }