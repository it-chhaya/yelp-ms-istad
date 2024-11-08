import Image from "next/image";
import Link from "next/link";

export default function Home() {
  return (
      <>
        <h1>Welcome to Yelp</h1>
        <Link href="/oauth2/authorization/yelp" className="bg-red-600 text-white">Log in</Link>
      </>
  );
}
