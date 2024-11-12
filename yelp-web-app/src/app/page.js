import Link from "next/link";
import { Terminal } from "lucide-react"

import {
    Alert,
    AlertDescription,
    AlertTitle,
} from "@/components/ui/alert"

export default function Home() {
  return (
      <>
        <h1>Welcome to Yelp</h1>
          <Alert>
              <Terminal className="h-4 w-4" />
              <AlertTitle>Heads up!</AlertTitle>
              <AlertDescription>
                  You can add components to your app using the cli.
              </AlertDescription>
          </Alert>
        <Link
            href="/oauth2/authorization/yelp"
            className="bg-red-800 text-white p-2 my-4 inline-block">Log in</Link>
      </>
  );
}
